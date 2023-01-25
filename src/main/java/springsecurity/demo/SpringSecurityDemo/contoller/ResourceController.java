package springsecurity.demo.SpringSecurityDemo.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forseti/api/v0/resource")
public class ResourceController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
