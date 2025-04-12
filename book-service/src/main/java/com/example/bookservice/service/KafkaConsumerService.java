package com.example.bookservice.service;


import com.example.shared.dto.KafkaAuthorCreatedEvent;

public interface KafkaConsumerService {
    void listenForCreatedAuthor(KafkaAuthorCreatedEvent event);
}
