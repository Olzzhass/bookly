package com.example.bookservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuthorDto {
    private String firstName;
    private String lastName;
    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
