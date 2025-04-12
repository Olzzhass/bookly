package com.example.authservice.service;

import com.example.authservice.model.AuthorRequest;
import com.example.authservice.model.dto.AuthorRequestDto;

import java.util.Optional;

public interface AuthorRequestService {
    void submitRequest(AuthorRequestDto requestDto, String name);

    Optional<AuthorRequest> findByUsername(String username);

    AuthorRequest save(AuthorRequest authorRequest);
}
