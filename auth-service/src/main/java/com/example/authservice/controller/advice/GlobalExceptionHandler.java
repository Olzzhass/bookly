package com.example.authservice.controller.advice;

import com.example.shared.dto.AppExceptionResponse;
import com.example.authservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<AppExceptionResponse> handleUserAlreadyExistsException (UserAlreadyExistException exception) {
        return buildExceptionResponse(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<AppExceptionResponse> handleIncorrectCredentialsException(IncorrectCredentialsException exception) {
        return buildExceptionResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppExceptionResponse> handleInvalidRequestException(InvalidRequestException exception) {
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(RefreshTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<AppExceptionResponse> handleRefreshTokenException(RefreshTokenException exception) {
        return buildExceptionResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppExceptionResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return buildExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }



    // Confusing of error types when developing

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    ResponseEntity<AppExceptionResponse> handleGeneralException(Exception exception) {
//        return buildExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
//    }

    private ResponseEntity<AppExceptionResponse> buildExceptionResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(AppExceptionResponse.builder()
                .status(status.value())
                .message(message)
                .build()
        );
    }
}
