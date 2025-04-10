package com.example.authservice.service;

import com.example.authservice.model.AuthUser;

import java.util.Optional;

public interface AuthUserService {
    Optional<AuthUser> findByUsername(String username);
    AuthUser save(String username, String email, String password);
    boolean isUserExists(String username);
    AuthUser save(AuthUser user);
}
