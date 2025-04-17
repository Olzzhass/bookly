package com.example.bookservice.repository;

import com.example.bookservice.model.BookSuggestion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookSuggestionRepository extends MongoRepository<BookSuggestion, String> {
}
