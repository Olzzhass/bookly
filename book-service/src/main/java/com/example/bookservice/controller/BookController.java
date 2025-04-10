package com.example.bookservice.controller;

import com.example.bookservice.model.dto.BookDto;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('AUTHOR')")
    public ResponseEntity<BookDto> create(@RequestBody BookDto dto, Authentication authentication) {
        return ResponseEntity.ok(bookService.save(dto, authentication.getName()));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR') or " +
            "(hasAuthority('AUTHOR') and @bookServiceImpl.isOwner(#id, #authentication.name))")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(){
        return ResponseEntity.ok(bookService.findAll());
    }
}
