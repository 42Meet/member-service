package kr.meet42.memberservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JWTProvider {
    private long expire = 60 * 24; // 1day
    private final Environment env;
    private String secretKey;

    public String generateToken(String content) throws Exception {
        secretKey = env.getProperty("token.secret");
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8")).compact();
    }
}
