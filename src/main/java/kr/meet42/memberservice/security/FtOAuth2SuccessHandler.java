package kr.meet42.memberservice.security;

import kr.meet42.memberservice.dto.TokenDto;
import kr.meet42.memberservice.service.MemberService;
import kr.meet42.memberservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class FtOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    private final JwtUtils jwtUtils;
    private final MemberService memberService;
    private final Environment env;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            DefaultOAuth2User principal = (DefaultOAuth2User)authentication.getPrincipal();
            Object login = principal.getAttributes().get("login");
            String accessToken = jwtUtils.generateAccessToken(login.toString());
            String refreshToken = jwtUtils.generateRefreshToken();
            TokenDto tokenDto = TokenDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            memberService.registerRefreshToken(refreshToken, login.toString());
            Cookie accessCookie = new Cookie("access-token", tokenDto.getAccessToken());
            Cookie refreshCookie = new Cookie("refresh-token", tokenDto.getRefreshToken());
            accessCookie.setMaxAge(60*60);
            accessCookie.setPath(env.getProperty("42meet.server.host"));
            refreshCookie.setMaxAge(60*60);
            refreshCookie.setPath(env.getProperty("42meet.server.host"));
            response.addCookie(accessCookie);
            response.addCookie(refreshCookie);
            response.sendRedirect(env.getProperty("42meet.server.redirect"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
