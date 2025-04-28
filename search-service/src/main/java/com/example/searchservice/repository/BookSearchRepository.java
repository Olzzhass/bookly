package com.example.searchservice.repository;

import com.example.searchservice.model.BookDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookSearchRepository extends ElasticsearchRepository<BookDocument, String> {
    List<BookDocument> findByNameContainingIgnoreCase(String name);

    @Query("{\"bool\": {\"should\": [" +
            "{\"match\": {\"name\": \"?0\"}}," +
            "{\"match\": {\"description\": \"?0\"}}" +
            "]}}")
    List<BookDocument> searchByKeyword(String keyword);
}
