package com.example.bookservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    private String id;

    private String name;
    private String description;
    private String isbn;
    private Integer publishedYear;
    private String language;

    private float averageRating;
    private int reviewCount;

    private List<AuthorEmbedded> authors;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
