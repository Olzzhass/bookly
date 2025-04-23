package com.example.searchservice.model;

import com.example.shared.dto.AuthorEmbedded;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "books")
public class BookDocument {

    @Id
    private String id;
    private String name;
    private String description;
    private String isbn;
    private LocalDateTime publishedDate;
    private String language;
    private List<AuthorEmbedded> authors;

}
