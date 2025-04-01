package com.example.authservice.service.impl;

import com.example.authservice.model.AuthUser;
import com.example.authservice.model.request.LoginRequest;
import com.example.authservice.model.request.RegistrationRequest;
import com.example.authservice.model.response.AuthResponse;
import com.example.authservice.service.AuthService;
import com.example.authservice.service.AuthUserService;
import com.example.authservice.service.PasswordService;
import com.example.authservice.util.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthUserService authUserService;
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final PasswordService passwordService;

    public AuthResponse login(LoginRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (AuthUser) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principal.getId(), principal.getUsername(), roles.get(0));

        return AuthResponse.builder()
                .accessToken(token)
                .build();
    }

    public AuthResponse registration(RegistrationRequest request) {

        if (authUserService.isUserExists(request.getEmail())) {
            return AuthResponse.builder()
                    .accessToken("Error: Email is already in use!") // TODO change to exception handling
                    .build();
        }

        String encodedPassword = passwordService.encodePassword(request.getPassword());
        AuthUser user = authUserService.save(request.getUsername(), request.getEmail(), encodedPassword);

        var token = jwtIssuer.issue(user.getId(), user.getUsername(), user.getRole().getAuthority());

        return AuthResponse.builder()
                .accessToken(token)
                .build();
    }
}
