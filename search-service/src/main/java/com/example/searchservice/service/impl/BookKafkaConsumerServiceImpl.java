package com.example.searchservice.service.impl;

import com.example.searchservice.model.BookDocument;
import com.example.searchservice.repository.BookSearchRepository;
import com.example.searchservice.service.BookKafkaConsumerService;
import com.example.shared.dto.BookCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookKafkaConsumerServiceImpl implements BookKafkaConsumerService {

    private final BookSearchRepository bookSearchRepository;

    @Override
    @KafkaListener(topics = "book-created", groupId = "search-service")
    public void consume(BookCreatedEvent event) {
        BookDocument book = BookDocument.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .isbn(event.getIsbn())
                .publishedDate(event.getPublishedDate())
                .language(event.getLanguage())
                .authors(event.getAuthors())
                .build();

        bookSearchRepository.save(book);
    }
}
