package com.example.userservice.service.impl;

import com.example.userservice.exception.UserProfileNotFoundException;
import com.example.userservice.mapper.UserProfileMapper;
import com.example.userservice.model.UserProfile;
import com.example.userservice.model.request.UserProfileRequest;
import com.example.userservice.model.response.UserProfileResponse;
import com.example.userservice.repository.UserProfileRepository;
import com.example.userservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    @Transactional
    public UserProfileResponse createProfile(UserProfileRequest request) {
        UserProfile profile = userProfileMapper.toEntity(request);
        setTimestampsForNewProfile(profile);
        UserProfile savedProfile = userProfileRepository.save(profile);
        return userProfileMapper.toResponse(savedProfile);
    }

    @Override
    public UserProfileResponse getProfileByCurrentUser() {
        String currentUsername = getCurrentUsername();
        UserProfile profile = findProfileOrThrow(currentUsername);
        return userProfileMapper.toResponse(profile);
    }

    @Override
    @Transactional
    public UserProfileResponse updateProfileForCurrentUser(UserProfileRequest request) {
        String currentUsername = getCurrentUsername();
        UserProfile profile = findProfileOrThrow(currentUsername);
        userProfileMapper.updateUserProfileFromDto(request, profile);
        updateTimestamp(profile);
        UserProfile savedProfile = userProfileRepository.save(profile);
        return userProfileMapper.toResponse(savedProfile);
    }

    private UserProfile findProfileOrThrow(String username) {
        return userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found with username: " + username));
    }

    private void setTimestampsForNewProfile(UserProfile profile) {
        LocalDateTime now = LocalDateTime.now();
        profile.setCreatedAt(now);
        profile.setUpdatedAt(now);
    }

    private String getCurrentUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username.replace("\"", "");
    }


    private void updateTimestamp(UserProfile profile) {
        profile.setUpdatedAt(LocalDateTime.now());
    }
}
