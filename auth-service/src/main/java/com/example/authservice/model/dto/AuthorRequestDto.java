package com.example.authservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorRequestDto {
    private String username;
    private String firstName;
    private String lastName;
    private String bio;
}
