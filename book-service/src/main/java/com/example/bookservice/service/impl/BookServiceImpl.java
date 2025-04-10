package com.example.bookservice.service.impl;

import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.model.Author;
import com.example.bookservice.model.Book;
import com.example.bookservice.model.dto.BookDto;
import com.example.bookservice.repository.AuthorRepository;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(BookDto dto, String username) {
        // TODO refactoring this code after main logic is completed
        Book book = bookMapper.toEntity(dto);
        Optional<Author> author = authorRepository.findByUsername(username);
        book.setAuthors(Collections.singletonList(author.get()));
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean isOwner(Long bookId, String username) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        return book.getAuthors().stream().anyMatch(author -> author.getUsername().equals(username));
    }
}
