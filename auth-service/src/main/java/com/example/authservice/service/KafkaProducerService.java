package com.example.authservice.service;


import com.example.shared.dto.KafkaAuthorCreatedEvent;

public interface KafkaProducerService {
    void sendAuthorCreatedEvent(KafkaAuthorCreatedEvent event);
}
