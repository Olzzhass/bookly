package com.example.userservice.controller.advice;

import com.example.shared.dto.AppExceptionResponse;
import com.example.userservice.exception.UserProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(UserAlreadyExistException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    ResponseEntity<AppExceptionResponse> handleUserAlreadyExistsException (UserAlreadyExistException exception) {
//        return buildExceptionResponse(HttpStatus.CONFLICT, exception.getMessage());
//    }

    @ExceptionHandler(UserProfileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppExceptionResponse> handleUserNotFoundException(UserProfileNotFoundException exception) {
        return buildExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    private ResponseEntity<AppExceptionResponse> buildExceptionResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(AppExceptionResponse.builder()
                .status(status.value())
                .message(message)
                .build()
        );
    }
}
