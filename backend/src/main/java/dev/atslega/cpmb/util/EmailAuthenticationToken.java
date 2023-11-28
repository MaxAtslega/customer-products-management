package dev.atslega.cpmb.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collection;

public class EmailAuthenticationToken implements Authentication {

    private final String userEmail;
    private final Long company;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAuthenticated;
    private WebAuthenticationDetails webAuthenticationDetails;

    public EmailAuthenticationToken(String userEmail, Long company, Collection<? extends GrantedAuthority> authorities) {
        this.userEmail = userEmail;
        this.company = company;
        this.authorities = authorities;
        this.isAuthenticated = true;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getCompany() {
        return company;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public WebAuthenticationDetails getDetails() {
        return this.webAuthenticationDetails;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }

    public void setDetails(WebAuthenticationDetails webAuthenticationDetails) {
        this.webAuthenticationDetails = webAuthenticationDetails;
    }
}
