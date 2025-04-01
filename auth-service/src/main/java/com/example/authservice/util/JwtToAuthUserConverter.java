package com.example.authservice.util;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authservice.model.AuthUser;
import com.example.authservice.model.type.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToAuthUserConverter {

    public AuthUser convert(DecodedJWT jwt) {
        return AuthUser.builder()
                .id(Long.valueOf(jwt.getSubject()))
                .username(jwt.getClaim("username").asString())
                .role(extractAuthoritiesFromClaim(jwt))
                .build();
    }

    private Role extractAuthoritiesFromClaim(DecodedJWT jwt){
        var claim = jwt.getClaim("role");
        if (claim.isNull() || claim.isMissing()) return null;
        return Role.valueOf(claim.asString().toUpperCase());
    }
}
