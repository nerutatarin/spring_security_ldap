package springsecurity.demo.SpringSecurityDemo.service.jwt;

import io.jsonwebtoken.Claims;
import springsecurity.demo.SpringSecurityDemo.contoller.model.User;

import java.util.Date;
import java.util.function.Function;

interface JwtTokenService {
    String getUsernameFromToken(String token);

    Date getExpirationDateFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    String generateToken(User userDetails);

    Boolean validateToken(String token, User userDetails);
}
