package com.example.authservice.service.impl;

import com.example.authservice.model.AuthUser;
import com.example.authservice.repository.AuthUserRepository;
import com.example.authservice.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;

    @Override
    public Optional<AuthUser> findByUsername(String username) {
        return authUserRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public AuthUser save(String username, String email, String password) {
        AuthUser user = AuthUser.builder()
                .username(username)
                .email(email)
                .password(password)
                .createdAt(LocalDateTime.now())
                .build();

        return authUserRepository.save(user);
    }

    @Override
    public boolean isUserExists(String username) {
        return authUserRepository.findByUsername(username).isPresent();
    }
}
