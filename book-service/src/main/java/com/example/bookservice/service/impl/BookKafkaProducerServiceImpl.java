package com.example.bookservice.service.impl;

import com.example.bookservice.service.BookKafkaProducerService;
import com.example.shared.dto.BookCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookKafkaProducerServiceImpl implements BookKafkaProducerService {

    private final KafkaTemplate<String, BookCreatedEvent> kafkaTemplate;

    @Override
    public void sendBookCreatedEvent(BookCreatedEvent bookCreatedEvent) {
        kafkaTemplate.send("book-created", bookCreatedEvent.getId(), bookCreatedEvent);
    }
}
