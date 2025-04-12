package com.example.authservice.service.impl;

import com.example.authservice.exception.UserAlreadyExistException;
import com.example.authservice.exception.UserNotFoundException;
import com.example.authservice.model.AuthUser;
import com.example.authservice.model.AuthorRequest;
import com.example.authservice.model.RefreshToken;
import com.example.authservice.model.type.RequestStatus;
import com.example.shared.dto.KafkaAuthorCreatedEvent;
import com.example.authservice.model.dto.UserRoleUpdateDto;
import com.example.authservice.model.request.LoginRequest;
import com.example.authservice.model.request.RegistrationRequest;
import com.example.authservice.model.response.AuthResponse;
import com.example.authservice.model.type.Role;
import com.example.authservice.service.*;
import com.example.authservice.util.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthUserService authUserService;
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final PasswordService passwordService;
    private final RefreshTokenService refreshTokenService;
    private final KafkaProducerService kafkaProducerService;
    private final AuthorRequestService authorRequestService;

    @Override
    public AuthResponse login(LoginRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (AuthUser) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = jwtIssuer.issue(principal.getId(), principal.getUsername(), roles.get(0));

        refreshTokenService.deleteByUser(principal);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(principal);

        return AuthResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    @Transactional
    public AuthResponse registration(RegistrationRequest request) {

        if (authUserService.isUserExists(request.getUsername())) {
            throw new UserAlreadyExistException("Пользователь с таким email или username уже существует");
        }

        String encodedPassword = passwordService.encodePassword(request.getPassword());
        AuthUser user = authUserService.save(request.getUsername(), request.getEmail(), encodedPassword);

        String token = jwtIssuer.issue(user.getId(), user.getUsername(), user.getRole().getAuthority());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofDays(1))
                .sameSite("Strict")
                .build();
    }

    @Override
    public void changeRole(UserRoleUpdateDto userRoleUpdateDto) {
        AuthUser user = findUserByUsername(userRoleUpdateDto.getUsername());

        AuthorRequest authorRequest = authorRequestService.findByUsername(userRoleUpdateDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Request not found"));

        user.setRole(userRoleUpdateDto.getRole());

        if (userRoleUpdateDto.getRole() == Role.AUTHOR) {
            kafkaProducerService.sendAuthorCreatedEvent(
                    new KafkaAuthorCreatedEvent(
                            authorRequest.getUsername(),
                            authorRequest.getFirstName(),
                            authorRequest.getLastName(),
                            authorRequest.getBio())
            );
        }

        authUserService.save(user);
    }

    private AuthUser findUserByUsername(String username) {
        return authUserService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
