package kr.meet42.memberservice.service;

import kr.meet42.memberservice.domain.entity.Member;
import kr.meet42.memberservice.dto.MemberDto;
import kr.meet42.memberservice.dto.TokenDto;

import java.util.List;

public interface MemberService {

    List<MemberDto> getMembers();

    Member getMember(String username);

    String getRole(String username);

    String join(MemberDto memberDto, String userRole);

    void registerRefreshToken(String refreshToken, String username);

    TokenDto verifyRefreshToken(String accessToken, String refreshToken);
}
