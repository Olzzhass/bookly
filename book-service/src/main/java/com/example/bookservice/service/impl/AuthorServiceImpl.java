package com.example.bookservice.service.impl;

import com.example.bookservice.mapper.AuthorMapper;
import com.example.bookservice.model.Author;
import com.example.bookservice.model.dto.AuthorDto;
import com.example.bookservice.repository.AuthorRepository;
import com.example.bookservice.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream().map(authorMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean existsByUsername(String username) {
        return authorRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> getByUsername(String username) {
        return authorRepository.findByUsername(username);
    }
}
