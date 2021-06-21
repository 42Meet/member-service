package kr.meet42.memberservice.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtils {
    private long expire = 60 * 24;
    private final Environment env;
    private String secretKey;

    public String extractFromToken(String tokenStr, String keyLocation) {
        String userLogin;

        try {
            secretKey = env.getProperty(keyLocation);
            DefaultJws defaultJws = (DefaultJws) Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(tokenStr);
            DefaultClaims claims = (DefaultClaims) defaultJws.getBody();
            userLogin = claims.getSubject();
            if (userLogin == null)
                return null;
        } catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            userLogin = null;
        }
        return userLogin;
    }

    public String generateAccessToken(String content) throws Exception {
        secretKey = env.getProperty("token.access-secret");
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8)).compact();
    }

    public String generateRefreshToken() throws Exception {
        secretKey = env.getProperty("token.refresh-secret");
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire * 14).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8)).compact();
    }
}
