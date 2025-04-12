package com.example.bookservice.service.impl;

import com.example.bookservice.model.Author;
import com.example.bookservice.service.AuthorService;
import com.example.bookservice.service.KafkaConsumerService;
import com.example.shared.dto.KafkaAuthorCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final AuthorService authorService;

    @Override
    @KafkaListener(topics = "author.created.topic", groupId = "book-service-group")
    public void listenForCreatedAuthor(KafkaAuthorCreatedEvent event) {

        if (authorService.existsByUsername(event.getUsername())) {
            return;
        }

        Author author = Author.builder()
                .username(event.getUsername())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .bio(event.getBio())
                .build();

        authorService.save(author);
    }
}
