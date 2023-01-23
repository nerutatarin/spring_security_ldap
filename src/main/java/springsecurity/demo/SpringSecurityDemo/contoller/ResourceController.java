package springsecurity.demo.SpringSecurityDemo.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ResourceController
{
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/")
    public String def(){return "welcome";}
}
