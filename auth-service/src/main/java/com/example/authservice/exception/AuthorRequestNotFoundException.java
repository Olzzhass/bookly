package com.example.authservice.exception;

public class AuthorRequestNotFoundException extends RuntimeException{
    public AuthorRequestNotFoundException(String message) {
        super(message);
    }
}
