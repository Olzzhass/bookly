package com.example.bookservice.service.impl;

import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.mapper.BookSuggestionMapper;
import com.example.bookservice.model.Book;
import com.example.bookservice.model.BookSuggestion;
import com.example.bookservice.model.dto.BookSuggestionDto;
import com.example.bookservice.model.type.BookSuggestionStatus;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.repository.BookSuggestionRepository;
import com.example.bookservice.service.BookSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSuggestionServiceImpl implements BookSuggestionService {

    private final BookSuggestionRepository bookSuggestionRepository;
    private final BookSuggestionMapper bookSuggestionMapper;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Override
    public List<BookSuggestionDto> getAll() {
        return bookSuggestionRepository.findAll().stream().map(bookSuggestionMapper::toDto).toList();
    }

    @Override
    @Transactional
    public BookSuggestionDto suggest(BookSuggestionDto bookSuggestionDto, String currentUsername) {
        currentUsername = currentUsername.replace("\"", "");

        BookSuggestion bookSuggestion = bookSuggestionMapper.toEntity(bookSuggestionDto);
        bookSuggestion.setSuggestedBy(currentUsername);
        return bookSuggestionMapper.toDto(bookSuggestionRepository.save(bookSuggestion));
    }

    @Override
    @Transactional
    public BookSuggestionDto approveSuggestion(String id) {
        BookSuggestion suggestion = bookSuggestionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suggestion not found")); // TODO exception handling

        suggestion.setStatus(BookSuggestionStatus.APPROVED);
        bookSuggestionRepository.save(suggestion);

        Book book = bookMapper.toBook(suggestion);
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());

        bookRepository.save(book);

        return bookSuggestionMapper.toDto(suggestion);
    }

    @Override
    @Transactional
    public BookSuggestionDto rejectSuggestion(String id, String moderatorMessage) {
        BookSuggestion suggestion = bookSuggestionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suggestion not found")); // TODO exception handling

        suggestion.setStatus(BookSuggestionStatus.REJECTED);
        suggestion.setModeratorMessage(moderatorMessage);

        bookSuggestionRepository.save(suggestion);
        return bookSuggestionMapper.toDto(suggestion);
    }
}
