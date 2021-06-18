package kr.meet42.memberservice.controller;

import kr.meet42.memberservice.domain.entity.Member;
import kr.meet42.memberservice.dto.MemberDto;
import kr.meet42.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<List<MemberDto>> getMembers() {
        List<MemberDto> findMembers = memberService.getMembers();
        return new ResponseEntity<>(findMembers, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("username") String username) {
        Member member = memberService.getMember(username);
        if (member == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(member.toDto(member), HttpStatus.OK);
        }
    }

    @GetMapping("/{username}/role")
    public ResponseEntity<String> getRole(@PathVariable("username") String username) {
        String memberRole = memberService.getRole(username);
        if (memberRole == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(memberRole, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> registerMember(@RequestBody MemberDto memberDto){
        if (memberService.join(memberDto, "ROLE_USER") == null)
            return new ResponseEntity<>("이미 가입된 아이디 입니다.", HttpStatus.CONFLICT);
        return new ResponseEntity<>(memberDto.getUsername(), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody MemberDto memberDto) {
        if (memberService.join(memberDto, "ROLE_ADMIN") == null)
            return new ResponseEntity<>("이미 가입된 아이디 입니다.", HttpStatus.CONFLICT);
        return new ResponseEntity<>(memberDto.getUsername(), HttpStatus.OK);
    }
}
