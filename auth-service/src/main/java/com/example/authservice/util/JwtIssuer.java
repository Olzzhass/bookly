package com.example.authservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties properties;
    public String issue(Long userId, String username, String role) {
        return JWT.create()
                .withClaim("userId", String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(30, ChronoUnit.MINUTES)))
                .withClaim("username", username)
                .withClaim("role", role)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}
