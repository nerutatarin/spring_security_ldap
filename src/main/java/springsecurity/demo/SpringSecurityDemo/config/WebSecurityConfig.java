/*
package springsecurity.demo.SpringSecurityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String API = "/forseti/api/v0/";
    private final String ENDPOINT_AUTH = API + "auth/**";
    private final String ENDPOINT_LOGIN = API + "auth/signin";
    private final String ENDPOINT_LOGOUT = API + "auth/logout";
    private final String ENDPOINT_RESOURCE = API + "resource/**";
    private final String ENDPOINT_HOME = API + "resource/home";

    @Value("${ldap.urls}")
    private String ldapUrls;

    @Value("${ldap.port}")
    private String ldapPort;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;

    @Value("${ldap.user.dn.pattern}")
    private String ldapUserDnPattern;

    @Value("${ldap.group.dn.pattern}")
    private String ldapGroupDnPattern;

    @Value("${ldap.search.filter}")
    private String ldapSearchFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()

                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers(ENDPOINT_AUTH).permitAll()
                .anyRequest().authenticated()

                //.and()
                //.formLogin()
                //.loginPage(ENDPOINT_LOGIN).permitAll()

                .and()
                .logout();
*/
/*                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(ENDPOINT_LOGOUT, "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl(ENDPOINT_LOGIN);*//*

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns(ldapUserDnPattern)
                .groupSearchBase(ldapGroupDnPattern)
                .contextSource()
                .url(ldapUrls + ":" + ldapPort + "/" + ldapBaseDn)

                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");
    }
}*/
