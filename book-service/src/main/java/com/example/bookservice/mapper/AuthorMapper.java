package com.example.bookservice.mapper;

import com.example.bookservice.model.Author;
import com.example.bookservice.model.dto.AuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorDto authorDto);
    AuthorDto toDto(Author author);
}
