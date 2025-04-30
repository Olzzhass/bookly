package com.example.shared.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreatedEvent {
    private String id;
    private String name;
    private String description;
    private String isbn;
    private Integer publishedYear;
    private String language;
    private List<AuthorEmbedded> authors;
}
