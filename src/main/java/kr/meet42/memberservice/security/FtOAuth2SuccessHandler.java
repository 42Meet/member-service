package kr.meet42.memberservice.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class FtOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    private final JWTProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            DefaultOAuth2User principal = (DefaultOAuth2User)authentication.getPrincipal();
            Object login = principal.getAttributes().get("login");
            response.addHeader("Authorization", jwtProvider.generateToken(login.toString()));
            response.sendRedirect("http://3.35.14.180/meeting/reservation");
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
