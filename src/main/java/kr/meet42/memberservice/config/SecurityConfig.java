package kr.meet42.memberservice.config;

import kr.meet42.memberservice.security.FtOAuth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/list")
                .authenticated();

        http.authorizeRequests()
                .antMatchers("*")
                .permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().disable();
        http.csrf().disable();;
        http.logout();
        http.oauth2Login()
            .successHandler(ftOAuth2SuccessHandler);
    }

    @Autowired
    private FtOAuth2SuccessHandler ftOAuth2SuccessHandler;
}
