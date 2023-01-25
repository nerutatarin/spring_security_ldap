package springsecurity.demo.SpringSecurityDemo.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;

@Component
public class JwtTokenProvider{

    public static String generateToken(String userName) {
        String jwtToken = Jwts.builder()
                .claim("name", userName)
                .setSubject("jwt_token")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentTimeMillis() + 60000 * 5))
                .signWith(SignatureAlgorithm.HS512, "forseti")
                .compact();
        return jwtToken;
    }
}
