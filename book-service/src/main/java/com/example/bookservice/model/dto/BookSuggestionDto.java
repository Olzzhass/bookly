package com.example.bookservice.model.dto;

import com.example.bookservice.model.AuthorEmbedded;
import com.example.bookservice.model.type.BookSuggestionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BookSuggestionDto {
    private String suggestedBy; // Имя пользователя кто предложил книгу
    private String name;
    private String description;
    private String isbn;
    private LocalDateTime publishedDate;
    private String language;
    private float averageRating;
    private int reviewCount;
    private List<AuthorEmbedded> authors;
    private LocalDateTime createdAt;
    private BookSuggestionStatus status; // WAITING, APPROVED, REJECTED
    private String moderatorMessage; // Причина отказа, если отклонено
}
