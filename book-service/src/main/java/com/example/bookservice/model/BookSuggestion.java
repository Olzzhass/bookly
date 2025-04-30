package com.example.bookservice.model;

import com.example.bookservice.model.type.BookSuggestionStatus;
import com.example.bookservice.model.type.BookSuggestionStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "book_suggestion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSuggestion {

    @Id
    private String id;

    private String suggestedBy; // Имя пользователя кто предложил книгу

    private String name;
    private String description;
    private String isbn;
    private Integer publishedYear;
    private String language;

    private float averageRating;
    private int reviewCount;
    private List<AuthorEmbedded> authors;

    private LocalDateTime createdAt;

    private BookSuggestionStatus status; // WAITING, APPROVED, REJECTED
    private String moderatorMessage; // Причина отказа, если отклонено
}
