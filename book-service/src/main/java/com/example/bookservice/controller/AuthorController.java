package com.example.bookservice.controller;

import com.example.bookservice.model.dto.AuthorDto;
import com.example.bookservice.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book-service/author")
public class AuthorController {

    private final AuthorService authorService;

    public ResponseEntity<List<AuthorDto>> getAll() {
        return ResponseEntity.ok(authorService.findAll());
    }
}
