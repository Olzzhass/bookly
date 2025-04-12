package com.example.authservice.model;

import com.example.authservice.model.type.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "tables", name = "author_requests")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private String bio;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
