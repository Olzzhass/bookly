package com.example.authservice.controller;

import com.example.authservice.model.dto.AuthorRequestDto;
import com.example.authservice.service.AuthorRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth-service/author-request")
public class AuthorRequestController {

    private final AuthorRequestService authorRequestService;

    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> requestAuthorRole(@RequestBody AuthorRequestDto requestDto, Principal principal) {
        authorRequestService.submitRequest(requestDto, principal.getName());
        return ResponseEntity.ok("Request submitted for review.");
    }

    // Request id
    @PostMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public ResponseEntity<String> approveRequest(@PathVariable Long id) {
        authorRequestService.approveRequest(id);
        return ResponseEntity.ok("Request approved.");
    }

    // Request id
    @PostMapping("/{id}/reject")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public ResponseEntity<String> rejectRequest(@PathVariable Long id) {
        authorRequestService.rejectRequest(id);
        return ResponseEntity.ok("Request rejected.");
    }
}
