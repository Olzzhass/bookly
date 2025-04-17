package com.example.authservice.service;

import com.example.authservice.model.dto.UserRoleUpdateDto;
import com.example.authservice.model.request.LoginRequest;
import com.example.authservice.model.request.RegistrationRequest;
import com.example.authservice.model.response.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    AuthResponse registration(RegistrationRequest request);

    ResponseCookie createRefreshTokenCookie(String refreshToken);

}
