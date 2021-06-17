package kr.meet42.memberservice.service;

import kr.meet42.memberservice.domain.entity.Member;
import kr.meet42.memberservice.domain.repository.MemberRepository;
import kr.meet42.memberservice.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    public Member getMember(String username) {
        Optional<Member> findMember = memberRepository.findByUsername(username);
        if (findMember.isPresent()) {
            Member member = findMember.get();
            return member;
        }
        return null;
    }

    @Transactional
    public List<MemberDto> getMembers() {
        List<MemberDto> memberDtoList = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
        for (Member m : memberList) {
            MemberDto memberDto = MemberDto.builder()
                    .id(m.getId())
                    .username(m.getUsername())
                    .role(m.getRole())
                    .build();
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    @Transactional
    public String getRole(String username) {
        Optional<Member> findMember = memberRepository.findByUsername(username);
        if (findMember.isPresent()) {
            Member member = findMember.get();
            return member.getRole();
        }
        return null;
    }

    @Transactional
    public String join(MemberDto memberDto, String userRole) {
        Optional<Member> findMember = memberRepository.findByUsername(memberDto.getUsername());
        if (findMember.isPresent()) {
            return null;
        }
        Member member = Member.builder()
                .username(memberDto.getUsername())
                .role(userRole)
                .build();
        memberRepository.save(member);
        return member.getUsername();
    }
}
