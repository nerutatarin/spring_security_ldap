package springsecurity.demo.SpringSecurityDemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LdapConfig {

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

    public String getLdapUrls() {
        return ldapUrls;
    }

    public void setLdapUrls(String ldapUrls) {
        this.ldapUrls = ldapUrls;
    }

    public String getLdapPort() {
        return ldapPort;
    }

    public void setLdapPort(String ldapPort) {
        this.ldapPort = ldapPort;
    }

    public String getLdapBaseDn() {
        return ldapBaseDn;
    }

    public void setLdapBaseDn(String ldapBaseDn) {
        this.ldapBaseDn = ldapBaseDn;
    }

    public String getLdapUserDnPattern() {
        return ldapUserDnPattern;
    }

    public void setLdapUserDnPattern(String ldapUserDnPattern) {
        this.ldapUserDnPattern = ldapUserDnPattern;
    }

    public String getLdapGroupDnPattern() {
        return ldapGroupDnPattern;
    }

    public void setLdapGroupDnPattern(String ldapGroupDnPattern) {
        this.ldapGroupDnPattern = ldapGroupDnPattern;
    }

    public String getLdapSearchFilter() {
        return ldapSearchFilter;
    }

    public void setLdapSearchFilter(String ldapSearchFilter) {
        this.ldapSearchFilter = ldapSearchFilter;
    }
}
