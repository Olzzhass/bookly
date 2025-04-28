package com.example.searchservice.model;

import com.example.searchservice.config.json.LocalDateTimeSerializer;
import com.example.searchservice.config.json.LocalDatetimeDeserializer;
import com.example.shared.dto.AuthorEmbedded;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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

    @Field(type = FieldType.Date, format = DateFormat.strict_date_optional_time)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDatetimeDeserializer.class)
    private LocalDateTime publishedDate;

    private String language;

    private List<AuthorEmbedded> authors;

}
