package com.example.authservice.controller;

import com.example.authservice.model.request.LoginRequest;
import com.example.authservice.model.request.RegistrationRequest;
import com.example.authservice.model.response.AuthResponse;
import com.example.authservice.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authServiceImpl.login(request));
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registration(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authServiceImpl.registration(request));
    }
}
