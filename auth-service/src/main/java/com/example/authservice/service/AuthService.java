package com.example.authservice.service;

import com.example.authservice.model.request.LoginRequest;
import com.example.authservice.model.request.RegistrationRequest;
import com.example.authservice.model.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    AuthResponse registration(RegistrationRequest request);
}
