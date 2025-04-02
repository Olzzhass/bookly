package com.example.authservice.model.request;

import com.example.authservice.exception.InvalidRequestException;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = false)
public class LoginRequest {
    String username;
    String password;

    @JsonAnySetter
    public void handleUnknownField(String key, Object value) {
        throw new InvalidRequestException("Поле '" + key + "' не разрешено в LoginRequest");
    }
}
