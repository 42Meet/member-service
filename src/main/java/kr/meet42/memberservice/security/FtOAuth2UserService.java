package kr.meet42.memberservice.security;

import kr.meet42.memberservice.domain.entity.Member;
import kr.meet42.memberservice.dto.MemberDto;
import kr.meet42.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FtOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + ":" + v);
        });
        String username = oAuth2User.getAttribute("login").toString();
        Member member = memberService.getMember(username);
        if (member == null) {
            MemberDto memberDto = MemberDto.builder()
                    .username(oAuth2User.getAttribute("login").toString())
                    .email(oAuth2User.getAttribute("email").toString())
                    .image_url(oAuth2User.getAttribute("image_url").toString())
                    .build();
            memberService.join(memberDto, "ROLE_USER");
        }

        return oAuth2User;
    }
}
