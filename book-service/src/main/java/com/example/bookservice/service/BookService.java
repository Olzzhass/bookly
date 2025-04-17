package com.example.bookservice.service;

import com.example.bookservice.model.Book;
import com.example.bookservice.model.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto createBook(BookDto bookDto);
}
