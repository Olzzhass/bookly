package com.example.authservice.repository;

import com.example.authservice.model.AuthorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRequestRepository extends JpaRepository<AuthorRequest, Long> {
    boolean existsByUsername(String username);

    Optional<AuthorRequest> findByUsername(String username);
}
