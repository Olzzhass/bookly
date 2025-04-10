package com.example.authservice.model.dto;

import com.example.authservice.model.type.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleUpdateDto {
    private String username;
    private Role role;
}
