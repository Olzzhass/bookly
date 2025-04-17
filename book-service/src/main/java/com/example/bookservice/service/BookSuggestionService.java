package com.example.bookservice.service;

import com.example.bookservice.model.dto.BookSuggestionDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookSuggestionService {
    List<BookSuggestionDto> getAll();

    BookSuggestionDto suggest(BookSuggestionDto bookSuggestionDto, String currentUserName);

    BookSuggestionDto approveSuggestion(String id);

    BookSuggestionDto rejectSuggestion(String id, String moderatorMessage);
}
