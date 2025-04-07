package com.example.userservice.controller;

import com.example.userservice.model.request.UserProfileRequest;
import com.example.userservice.model.response.UserProfileResponse;
import com.example.userservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<UserProfileResponse> createProfile(@RequestBody UserProfileRequest request) {
        return ResponseEntity.ok(userProfileService.createProfile(request));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> getProfile(@PathVariable String username) {
        return ResponseEntity.ok(userProfileService.getProfileByUsername(username));
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserProfileResponse> updateProfile(@PathVariable String username, @RequestBody UserProfileRequest request) {
        return ResponseEntity.ok(userProfileService.updateProfile(username, request));
    }
}
