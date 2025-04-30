package com.example.bookservice.model.dto;

import com.example.bookservice.model.AuthorEmbedded;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BookDto {
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
