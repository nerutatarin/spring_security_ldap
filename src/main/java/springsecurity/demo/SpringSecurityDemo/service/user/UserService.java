package springsecurity.demo.SpringSecurityDemo.service.user;

import org.springframework.stereotype.Service;
import springsecurity.demo.SpringSecurityDemo.contoller.model.User;

@Service
public class UserService {
    public User loadUserByUsername(String name) {
        User u = new User();
        u.setId(1l);
        u.setUsername(name);
        return u;
    }
}
