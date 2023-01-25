package springsecurity.demo.SpringSecurityDemo.contoller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthResponse
{
    private String token;
}
