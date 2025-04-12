package com.example.authservice.controller;

import com.example.authservice.model.dto.AuthorRequestDto;
import com.example.authservice.model.dto.UserRoleUpdateDto;
import com.example.authservice.model.request.LoginRequest;
import com.example.authservice.model.request.RegistrationRequest;
import com.example.authservice.model.response.AuthResponse;
import com.example.authservice.service.AuthService;
import com.example.authservice.service.AuthorRequestService;
import com.example.authservice.service.RefreshTokenService;
import com.example.authservice.service.impl.AuthServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    // TODO Разделить логику на несколько контроллеров -> AuthorRequestController, mayBe TokenController

    private final AuthService authService;
    private final AuthorRequestService authorRequestService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request,
                                              HttpServletResponse response) {
        AuthResponse authResponse = authService.login(request);

        addRefreshTokenCookie(response, request.getUsername());

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registration(@RequestBody RegistrationRequest request,
                                                     HttpServletResponse response) {
        AuthResponse authResponse = authService.registration(request);

        addRefreshTokenCookie(response, request.getUsername());

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshAccessToken(@CookieValue(value = "refreshToken", required = false)
                                                               String refreshToken) {
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(refreshTokenService.refreshAccessToken(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                       HttpServletResponse response) {
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        refreshTokenService.logout(refreshToken, response);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Successful test of method security");
    }

    @PostMapping("/author-request")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> requestAuthorRole(@RequestBody AuthorRequestDto requestDto, Principal principal) {
        authorRequestService.submitRequest(requestDto, principal.getName());
        return ResponseEntity.ok("Request submitted for review.");
    }

    @PutMapping("/change-role")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public ResponseEntity<Void> changeRole(@RequestBody UserRoleUpdateDto userRoleUpdateDto) {
        authService.changeRole(userRoleUpdateDto);

        return ResponseEntity.noContent().build();
    }

    private void addRefreshTokenCookie(HttpServletResponse response, String username) {
        ResponseCookie refreshTokenCookie = authService.createRefreshTokenCookie(
                refreshTokenService.findTokenByUser(username)
        );
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
    }
}
