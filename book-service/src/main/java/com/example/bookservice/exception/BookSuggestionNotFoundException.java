package com.example.bookservice.exception;

public class BookSuggestionNotFoundException extends RuntimeException{
    public BookSuggestionNotFoundException(String message) {
        super(message);
    }
}
