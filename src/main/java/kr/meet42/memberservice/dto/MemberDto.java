package kr.meet42.memberservice.dto;

import kr.meet42.memberservice.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {

    private Long id;
    private String username;
    private String image_url;
    private String email;
    private String role;
    private String refreshToken;

    public Member toEntity(MemberDto memberDto){
        Member build = Member.builder()
                .id(memberDto.getId())
                .username(memberDto.getUsername())
                .image_url(memberDto.getImage_url())
                .email(memberDto.getEmail())
                .role(memberDto.getRole())
                .refreshToken(memberDto.getRefreshToken())
                .build();
        return build;
    }
}
