package com.example.authservice.exception;

public class DuplicateAuthorRequestException extends RuntimeException {
    public DuplicateAuthorRequestException(String message) {
        super(message);
    }
}
