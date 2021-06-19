package kr.meet42.memberservice.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class FtOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

}
