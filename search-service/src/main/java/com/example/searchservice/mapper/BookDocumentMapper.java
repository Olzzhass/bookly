package com.example.searchservice.mapper;

import com.example.searchservice.model.BookDocument;
import com.example.searchservice.model.dto.BookDocumentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDocumentMapper {
    BookDocument toEntity(BookDocumentDto bookDto);
    BookDocumentDto toDto(BookDocument book);
}
