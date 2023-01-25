package springsecurity.demo.SpringSecurityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springsecurity.demo.SpringSecurityDemo.security.jwt.JwtAuthenticationEntryPoint;
import springsecurity.demo.SpringSecurityDemo.security.jwt.JwtTokenProvider;
import springsecurity.demo.SpringSecurityDemo.security.ldap.LdapAuthenticationProvider;
import springsecurity.demo.SpringSecurityDemo.service.jwt.JwtRequestFilter;

import javax.management.relation.Role;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String API = "/forseti/api/v0/";
    private final String ENDPOINT_AUTH = API + "auth/**";
    private final String ENDPOINT_LOGIN = API + "auth/authenticate";
    private final String ENDPOINT_LOGOUT = API + "auth/logout";
    private final String ENDPOINT_RESOURCE = API + "resource/**";
    private final String ENDPOINT_HOME = API + "resource/home";

    @Autowired
    private LdapAuthenticationProvider ldapAuthenticationProvider;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(ldapAuthenticationProvider)
                .eraseCredentials(false);
                /*.ldapAuthentication()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");*/
    }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers(ENDPOINT_AUTH).permitAll()
                .anyRequest().authenticated()

                //.and()
                //.formLogin()
                //.successForwardUrl(ENDPOINT_LOGIN)
                //.loginPage(ENDPOINT_LOGIN).permitAll()

                .and()
                .logout()

                .and()
                .csrf().disable().httpBasic();

    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()

                .and()
                .csrf().disable()
                .headers()
                .frameOptions()
                .deny()

                .and()
                .authorizeRequests()
                .antMatchers(API + "/login").permitAll()
                .antMatchers(ENDPOINT_HOME)
                .hasAnyRole()
                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

   /* @Bean
    public JwtTokenProvider provider() {
        return new JwtTokenProvider();
    }*/
}
