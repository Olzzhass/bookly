package com.example.userservice.service.impl;

import com.example.userservice.mapper.UserProfileMapper;
import com.example.userservice.model.UserProfile;
import com.example.userservice.model.request.UserProfileRequest;
import com.example.userservice.model.response.UserProfileResponse;
import com.example.userservice.repository.UserProfileRepository;
import com.example.userservice.service.UserProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    // TODO Exception handling and segregation of logics in methods

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse createProfile(UserProfileRequest request) {
        UserProfile entity = userProfileMapper.toEntity(request);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return userProfileMapper.toResponse(userProfileRepository.save(entity));
    }

    @Override
    public UserProfileResponse getProfileByUsername(String username) {
        UserProfile profile = userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));

        return userProfileMapper.toResponse(profile);
    }

    @Override
    public UserProfileResponse updateProfile(String username, UserProfileRequest request) {
        UserProfile profile = userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
        userProfileMapper.updateUserProfileFromDto(request, profile);
        profile.setUpdatedAt(LocalDateTime.now());
        return userProfileMapper.toResponse(userProfileRepository.save(profile));
    }
}
