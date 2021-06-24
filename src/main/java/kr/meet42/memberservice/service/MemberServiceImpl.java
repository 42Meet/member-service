package kr.meet42.memberservice.service;

import kr.meet42.memberservice.domain.entity.Member;
import kr.meet42.memberservice.domain.repository.MemberRepository;
import kr.meet42.memberservice.dto.MemberDto;
import kr.meet42.memberservice.dto.TokenDto;
import kr.meet42.memberservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;

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
                    .email(m.getEmail())
                    .image_url(m.getImage_url())
                    .role(m.getRole())
                    .refreshToken(m.getRefreshToken())
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
                .email(memberDto.getEmail())
                .image_url(memberDto.getImage_url())
                .role(userRole)
                .build();
        memberRepository.save(member);
        return member.getUsername();
    }

    @Transactional
    public void registerRefreshToken(String refreshToken, String username) {
        Optional<Member> findMember = memberRepository.findByUsername(username);
        findMember.ifPresent(member -> member.setRefreshToken(refreshToken));
    }

    @Transactional
    public TokenDto verifyRefreshToken(TokenDto tokenDto) {
        String refreshToken = tokenDto.getRefreshToken();
        if (!jwtUtils.validateRefreshToken(refreshToken)) {
            return null;
        }
        String userLogin = jwtUtils.extractFromToken(tokenDto.getAccessToken(), "token.access-secret");
        Optional<Member> findMember = memberRepository.findByUsername(userLogin);
        if (findMember.isPresent()) {
            Member member = findMember.get();
            String storedRefreshToken = member.getRefreshToken();
            if (storedRefreshToken.equals(tokenDto.getRefreshToken())) {
                try {
                    String newToken = jwtUtils.generateAccessToken(userLogin);
                    return TokenDto.builder()
                            .accessToken(newToken)
                            .refreshToken(tokenDto.getRefreshToken())
                            .build();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }
}
