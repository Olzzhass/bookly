package com.example.bookservice.service;

import com.example.bookservice.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();

}
