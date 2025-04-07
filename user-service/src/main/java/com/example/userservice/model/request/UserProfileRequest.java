package com.example.userservice.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
