package com.example.authservice.service.impl;

import com.example.authservice.model.AuthorRequest;
import com.example.authservice.model.dto.AuthorRequestDto;
import com.example.authservice.model.type.RequestStatus;
import com.example.authservice.repository.AuthorRequestRepository;
import com.example.authservice.service.AuthorRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorRequestServiceImpl implements AuthorRequestService {

    private final AuthorRequestRepository authorRequestRepository;

    @Override
    public void submitRequest(AuthorRequestDto requestDto, String username) {
        if (authorRequestRepository.existsByUsername(username)) {
            throw new RuntimeException("You already submitted a request."); // TODO exception handling
        }

        AuthorRequest request = AuthorRequest.builder()
                .username(requestDto.getUsername())
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .bio(requestDto.getBio())
                .status(RequestStatus.PENDING)
                .build();

        authorRequestRepository.save(request);
    }

    @Override
    public Optional<AuthorRequest> findByUsername(String username) {
        return authorRequestRepository.findByUsername(username);
    }

    @Override
    public AuthorRequest save(AuthorRequest authorRequest) {
        return authorRequestRepository.save(authorRequest);
    }
}
