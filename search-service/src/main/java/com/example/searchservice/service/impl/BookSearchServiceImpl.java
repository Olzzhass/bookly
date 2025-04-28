package com.example.searchservice.service.impl;

import com.example.searchservice.mapper.BookDocumentMapper;
import com.example.searchservice.model.dto.BookDocumentDto;
import com.example.searchservice.repository.BookSearchRepository;
import com.example.searchservice.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookSearchServiceImpl implements BookSearchService {

    private final BookSearchRepository bookSearchRepository;
    private final BookDocumentMapper bookDocumentMapper;

    @Override
    public List<BookDocumentDto> searchBooks(String keyword) {
        return bookSearchRepository.searchByKeyword(keyword)
                .stream().map(bookDocumentMapper::toDto).toList();
    }

    @Override
    public List<BookDocumentDto> searchByName(String name) {
        return bookSearchRepository.findByNameContainingIgnoreCase(name)
                .stream().map(bookDocumentMapper::toDto).toList();
    }

    @Override
    public List<BookDocumentDto> getAllBooks() {
        return toList(bookSearchRepository.findAll())
                .stream().map(bookDocumentMapper::toDto).toList();
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
