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
    private String role;

    public Member toEntity(MemberDto memberDto){
        Member build = Member.builder()
                .id(memberDto.getId())
                .username(memberDto.getUsername())
                .role(memberDto.getRole())
                .build();
        return build;
    }
}
