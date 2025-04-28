package com.example.searchservice.service;

import com.example.searchservice.model.dto.BookDocumentDto;

import java.util.List;

public interface BookSearchService {
    List<BookDocumentDto> searchBooks(String keyword);
    List<BookDocumentDto> searchByName(String name);
    List<BookDocumentDto> getAllBooks();
}
