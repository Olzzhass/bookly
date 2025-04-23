package com.example.searchservice.service;

import com.example.shared.dto.BookCreatedEvent;

public interface BookKafkaConsumerService {
    void consume(BookCreatedEvent event);
}
