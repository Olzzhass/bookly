package com.example.userservice.mapper;

import com.example.userservice.model.UserProfile;
import com.example.userservice.model.request.UserProfileRequest;
import com.example.userservice.model.response.UserProfileResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toEntity(UserProfileRequest request);

    UserProfileResponse toResponse(UserProfile userProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserProfileFromDto(UserProfileRequest dto, @MappingTarget UserProfile entity);
}
