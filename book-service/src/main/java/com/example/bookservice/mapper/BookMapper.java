package com.example.bookservice.mapper;

import com.example.bookservice.model.Book;
import com.example.bookservice.model.BookSuggestion;
import com.example.bookservice.model.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookDto bookDto);
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Book toBook(BookSuggestion suggestion);
}
