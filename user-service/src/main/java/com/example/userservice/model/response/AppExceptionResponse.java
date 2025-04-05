package com.example.userservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AppExceptionResponse {

    private int status;

    private String message;

    @Builder.Default
    private Date timestamp = new Date();
}
