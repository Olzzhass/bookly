package com.example.bookservice.mapper;

import com.example.bookservice.model.Book;
import com.example.bookservice.model.BookSuggestion;
import com.example.bookservice.model.dto.BookDto;
import com.example.bookservice.model.dto.BookSuggestionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookSuggestionMapper {
    BookSuggestion toEntity(BookSuggestionDto bookSuggestionDto);
    BookSuggestionDto toDto(BookSuggestion bookSuggestion);
}
