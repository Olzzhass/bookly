package com.example.userservice.service;

import com.example.userservice.model.request.UserProfileRequest;
import com.example.userservice.model.response.UserProfileResponse;

public interface UserProfileService {
    UserProfileResponse createProfile(UserProfileRequest request);
    UserProfileResponse getProfileByCurrentUser();
    UserProfileResponse updateProfileForCurrentUser(UserProfileRequest request);
}
