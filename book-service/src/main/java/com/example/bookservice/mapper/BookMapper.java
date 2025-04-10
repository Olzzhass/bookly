package com.example.bookservice.mapper;

import com.example.bookservice.model.Book;
import com.example.bookservice.model.dto.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookDto bookDto);
    BookDto toDto(Book book);
}
