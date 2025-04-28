package com.example.searchservice.model.dto;

import com.example.shared.dto.AuthorEmbedded;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDocumentDto {
    private String id;
    private String name;
    private String description;
    private String language;
    private List<AuthorEmbedded> authors;
}
