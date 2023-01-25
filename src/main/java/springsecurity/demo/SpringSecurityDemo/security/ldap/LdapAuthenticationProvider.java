package springsecurity.demo.SpringSecurityDemo.security.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import springsecurity.demo.SpringSecurityDemo.config.LdapConfig;

import java.util.ArrayList;
import java.util.List;

@Component
public class LdapAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LdapConfig ldapConfig;
    @Autowired
    private LdapContextSource contextSource;

    private LdapTemplate buildLdapTemplate() {
        contextSource.setUrl(ldapConfig.getLdapUrls() + ":" + ldapConfig.getLdapPort());
        contextSource.setBase(ldapConfig.getLdapBaseDn());
        //contextSource.setUserDn(ldapConfig.getLdapUserDnPattern());
        contextSource.setAnonymousReadOnly(true);
        contextSource.setReferral("follow");

        //contextSource.setBase(ldapConfig.getLdapGroupDnPattern());
        contextSource.afterPropertiesSet();

        return new LdapTemplate(contextSource);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LdapTemplate ldapTemplate = buildLdapTemplate();

        Filter filter = new EqualsFilter("sAMAccountName", authentication.getName());

        Boolean authenticate = isAuthenticate(authentication, ldapTemplate, filter);

        if (authenticate) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            UserDetails userDetails = new User(
                    authentication.getName(),
                    authentication.getCredentials().toString(),
                    grantedAuthorities);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    authentication.getCredentials().toString(),
                    grantedAuthorities);

            return auth;
        } else {
            return null;
        }
    }

    private Boolean isAuthenticate(Authentication authentication, LdapTemplate ldapTemplate, Filter filter) {
        return ldapTemplate.authenticate(
                LdapUtils.emptyLdapName(),
                filter.encode(),
                authentication.getCredentials().toString());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
