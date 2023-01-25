package springsecurity.demo.SpringSecurityDemo.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springsecurity.demo.SpringSecurityDemo.contoller.model.UserAuthRequest;
import springsecurity.demo.SpringSecurityDemo.service.auth.AuthenticationService;

@RestController
//@RequestMapping("/forseti/api/v0/auth")
@RequestMapping("/forseti/api/v0/login")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    //@PostMapping("/authenticate")
    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody UserAuthRequest request) throws Exception {
        return authenticationService.authenticate(request);
    }

    /*@GetMapping("/logout")
    private ResponseEntity<?> logout() {
        return ResponseEntity.ok().body("logout");
    }*/
}
