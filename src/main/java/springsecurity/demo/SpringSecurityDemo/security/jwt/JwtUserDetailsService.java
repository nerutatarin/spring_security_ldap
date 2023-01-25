package springsecurity.demo.SpringSecurityDemo.security.jwt;

import springsecurity.demo.SpringSecurityDemo.contoller.model.User;

public class JwtUserDetailsService {
    public User loadUserByUsername(String username) {
        User u = new User();
        u.setUsername(username);
        u.setId(1l);
        return u;
    }
}
