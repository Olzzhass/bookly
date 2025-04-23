package com.example.bookservice.service;

import com.example.shared.dto.BookCreatedEvent;

public interface BookKafkaProducerService {
    void sendBookCreatedEvent(BookCreatedEvent bookCreatedEvent);
}
