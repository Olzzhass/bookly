package com.example.authservice.service.impl;

import com.example.authservice.service.KafkaProducerService;
import com.example.shared.dto.KafkaAuthorCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, KafkaAuthorCreatedEvent> kafkaTemplate;

    @Override
    public void sendAuthorCreatedEvent(KafkaAuthorCreatedEvent event) {
        kafkaTemplate.send("author.created.topic", event);
    }
}
