package com.example.bookservice.controller.advice;

import com.example.bookservice.exception.BookSuggestionNotFoundException;
import com.example.shared.dto.AppExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookSuggestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<AppExceptionResponse> handleUserAlreadyExistsException (BookSuggestionNotFoundException exception) {
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
