package com.example.authservice.util;

import com.example.authservice.model.AuthUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthUserAuthenticationToken extends AbstractAuthenticationToken {
    private final AuthUser authUser;

    public AuthUserAuthenticationToken(AuthUser authUser) {
        super(authUser.getAuthorities());
        this.authUser = authUser;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public AuthUser getPrincipal() {
        return authUser;
    }
}
