/*
package springsecurity.demo.SpringSecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springsecurity.demo.SpringSecurityDemo.test.security.JwtAuthenticationEntryPoint;
import springsecurity.demo.SpringSecurityDemo.test.security.config.OpenLdapAuthenticationProvider;
import springsecurity.demo.SpringSecurityDemo.test.security.filter.JwtRequestFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {

    private OpenLdapAuthenticationProvider openLdapAuthenticationProvider;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig2(OpenLdapAuthenticationProvider openLdapAuthenticationProvider,
                              JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                              JwtRequestFilter jwtRequestFilter) {
        this.openLdapAuthenticationProvider = openLdapAuthenticationProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(openLdapAuthenticationProvider);
    }

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
                .antMatchers("/api/login").permitAll()
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

}
*/
