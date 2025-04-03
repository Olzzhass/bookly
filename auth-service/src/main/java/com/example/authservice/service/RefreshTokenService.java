package com.example.authservice.service;

import com.example.authservice.model.AuthUser;
import com.example.authservice.model.RefreshToken;
import com.example.authservice.model.response.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(AuthUser user);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(AuthUser user);
    RefreshToken verifyExpiration(RefreshToken token);
    String findTokenByUser(String username);
    AuthResponse refreshAccessToken(String refreshToken);
    void logout(String refreshToken, HttpServletResponse response);
    void deleteByToken(RefreshToken token);
}
