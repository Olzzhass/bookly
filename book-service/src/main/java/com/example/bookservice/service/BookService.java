package com.example.bookservice.service;

import com.example.bookservice.model.Author;
import com.example.bookservice.model.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto save(BookDto dto, String username);

    void delete(Long id);

    List<BookDto> findAll();

    boolean isOwner(Long bookId, String username);

    Author getAuthorByUsername(String username);
}
