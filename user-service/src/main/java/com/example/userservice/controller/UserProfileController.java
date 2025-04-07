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

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        return ResponseEntity.ok(userProfileService.getProfileByCurrentUser());
    }

    @PutMapping("/me")
    public ResponseEntity<UserProfileResponse> updateProfile(@RequestBody UserProfileRequest request) {
        return ResponseEntity.ok(userProfileService.updateProfileForCurrentUser(request));
    }
}
