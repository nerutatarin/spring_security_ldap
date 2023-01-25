package springsecurity.demo.SpringSecurityDemo.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.ContainerCriteria;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import springsecurity.demo.SpringSecurityDemo.contoller.model.User;
import springsecurity.demo.SpringSecurityDemo.contoller.model.UserAuthRequest;
import springsecurity.demo.SpringSecurityDemo.security.jwt.JwtTokenProvider;
import springsecurity.demo.SpringSecurityDemo.security.ldap.LdapAuthenticationProvider;
import springsecurity.demo.SpringSecurityDemo.service.jwt.JwtTokenServiceImpl;
import springsecurity.demo.SpringSecurityDemo.service.user.UserService;

import java.util.List;

@Service
public class AuthenticationService {

    private static final String USER_DISABLED = "USER DISABLED";
    private static final String INVALID_CREDENTIALS = "INVALID CREDENTIALS";

    @Autowired
    private LdapAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private JwtTokenServiceImpl jwtTokenService;
    @Autowired
    private LdapTemplate ldapTemplate;
    @Autowired
    private UserService userService;

    public ResponseEntity<?> authenticate(UserAuthRequest request) throws Exception {

        ContainerCriteria sAMAccountName = LdapQueryBuilder.query()
                .where("sAMAccountName")
                .is(request.getLogin());


        authenticate(request.getLogin(), request.getPassword());

        final User userDetails = userService.loadUserByUsername(request.getLogin());
        final String token = jwtTokenService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private String getToken(String username) {
        String principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();


        return JwtTokenProvider.generateToken(
                username
        );
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationProvider
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception(USER_DISABLED, e);
        } catch (BadCredentialsException e) {
            throw new Exception(INVALID_CREDENTIALS, e);
        }
    }
}
