package com.example.bookservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorEmbedded {
    private String firstName;
    private String lastName;
    private String bio;
}
