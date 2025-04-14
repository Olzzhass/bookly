package com.example.bookservice.service;

import com.example.bookservice.model.Author;
import com.example.bookservice.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDto> findAll();

    boolean existsByUsername(String username);

    Author save(Author author);

    Optional<Author> getByUsername(String username);
}
