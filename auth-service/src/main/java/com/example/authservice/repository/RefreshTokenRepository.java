package com.example.authservice.repository;

import com.example.authservice.model.AuthUser;
import com.example.authservice.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(AuthUser user);

    Optional<RefreshToken> findByUser(AuthUser user);
}
