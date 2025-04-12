package com.example.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaAuthorCreatedEvent {
    private String username;
    private String firstName;
    private String lastName;
    private String bio;
}
