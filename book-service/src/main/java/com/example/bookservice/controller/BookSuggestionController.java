package com.example.bookservice.controller;


import com.example.bookservice.model.dto.BookSuggestionDto;
import com.example.bookservice.service.BookSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-service/book-suggestions")
@RequiredArgsConstructor
public class BookSuggestionController {

    private final BookSuggestionService bookSuggestionService;

    @GetMapping
    @PreAuthorize("hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public ResponseEntity<List<BookSuggestionDto>> getAll() {
        return ResponseEntity.ok(bookSuggestionService.getAll());
    }

    @PostMapping
    public ResponseEntity<BookSuggestionDto> suggest(@RequestBody BookSuggestionDto bookSuggestionDto,
                                                     Authentication authentication) {
        return ResponseEntity.ok(bookSuggestionService.suggest(bookSuggestionDto, authentication.getName()));
    }

    @PatchMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public ResponseEntity<BookSuggestionDto> approve(@PathVariable String id) {
        return ResponseEntity.ok(bookSuggestionService.approveSuggestion(id));
    }

    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public ResponseEntity<BookSuggestionDto> reject(@PathVariable String id, @RequestParam String moderatorMessage) {
        return ResponseEntity.ok(bookSuggestionService.rejectSuggestion(id, moderatorMessage));
    }
}
