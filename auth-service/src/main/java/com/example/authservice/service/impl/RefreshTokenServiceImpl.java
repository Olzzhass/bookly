package com.example.authservice.service.impl;

import com.example.authservice.exception.RefreshTokenException;
import com.example.authservice.exception.UserNotFoundException;
import com.example.authservice.model.AuthUser;
import com.example.authservice.model.RefreshToken;
import com.example.authservice.model.response.AuthResponse;
import com.example.authservice.repository.AuthUserRepository;
import com.example.authservice.repository.RefreshTokenRepository;
import com.example.authservice.service.RefreshTokenService;
import com.example.authservice.util.JwtIssuer;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthUserRepository authUserRepository;
    private final JwtIssuer jwtIssuer;

    @Override
    @Transactional
    public RefreshToken createRefreshToken(AuthUser user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusDays(1))
                .createdAt(LocalDateTime.now())
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public void deleteByUser(AuthUser user) {
        refreshTokenRepository.deleteByUser(user);
    }

    @Override
    @Transactional
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenException("Refresh token has expired. Please login again.");
        }
        return token;
    }

    @Override
    public String findTokenByUser(String username) {
        return authUserRepository.findByUsername(username)
                .map(user -> refreshTokenRepository.findByUser(user)
                        .map(RefreshToken::getToken)
                        .orElseThrow(() -> new RefreshTokenException("Refresh token для пользователя не найден")))
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден: " + username));
    }

    @Override
    @Transactional
    public AuthResponse refreshAccessToken(String refreshToken) {
        RefreshToken token = findByToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenException("Invalid refresh token"));

        verifyExpiration(token);

        AuthUser user = token.getUser();

        var roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String newAccessToken = jwtIssuer.issue(user.getId(), user.getUsername(), roles.get(0));

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .build();
    }

    @Override
    @Transactional
    public void logout(String refreshToken, HttpServletResponse response) {
        RefreshToken token = findByToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenException("Invalid refresh token"));

        deleteByToken(token);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofSeconds(0))
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    @Override
    public void deleteByToken(RefreshToken token) {
        refreshTokenRepository.delete(token);
    }
}
