package com.example.searchservice.controller;

import com.example.searchservice.model.dto.BookDocumentDto;
import com.example.searchservice.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search-service")
public class BookSearchController {

    private final BookSearchService bookSearchService;

    @GetMapping
    public ResponseEntity<List<BookDocumentDto>> searchBooks(@RequestParam String keyword) {
        return ResponseEntity.ok(bookSearchService.searchBooks(keyword));
    }

    @GetMapping("/name")
    public ResponseEntity<List<BookDocumentDto>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(bookSearchService.searchByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDocumentDto>> getAllBooks() {
        return ResponseEntity.ok(bookSearchService.getAllBooks());
    }
}
