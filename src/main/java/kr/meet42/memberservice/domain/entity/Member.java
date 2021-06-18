package kr.meet42.memberservice.domain.entity;

import kr.meet42.memberservice.dto.MemberDto;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@ToString(of = {"id", "username", "role"})
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Column(length = 50, nullable = false)
    private String image_url;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    public MemberDto toDto(Member member){
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .image_url(member.getImage_url())
                .email(member.getEmail())
                .role(member.getRole())
                .build();
        return memberDto;
    }
}
