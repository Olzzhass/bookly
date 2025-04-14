package com.example.authservice.service.impl;

import com.example.authservice.exception.AuthorRequestNotFoundException;
import com.example.authservice.exception.DuplicateAuthorRequestException;
import com.example.authservice.model.AuthorRequest;
import com.example.authservice.model.dto.AuthorRequestDto;
import com.example.authservice.model.type.RequestStatus;
import com.example.authservice.repository.AuthorRequestRepository;
import com.example.authservice.service.AuthService;
import com.example.authservice.service.AuthorRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorRequestServiceImpl implements AuthorRequestService {

    private final AuthorRequestRepository authorRequestRepository;
    private final AuthService authService;

    @Override
    public void submitRequest(AuthorRequestDto requestDto, String username) {
        if (authorRequestRepository.existsByUsername(username)) {
            throw new DuplicateAuthorRequestException("You already submitted a request.");
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
    public AuthorRequest findById(Long requestId) {
        return authorRequestRepository.findById(requestId)
                .orElseThrow(() -> new AuthorRequestNotFoundException("Author request not found"));
    }

    @Override
    public AuthorRequest save(AuthorRequest authorRequest) {
        return authorRequestRepository.save(authorRequest);
    }

    @Override
    public void approveRequest(Long id) {
        AuthorRequest authorRequest = findById(id);

        if (authorRequest.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Author request is not in PENDING status");
        }

        authService.changeRoleToAdmin(authorRequest);
        authorRequest.setStatus(RequestStatus.APPROVED);
        authorRequestRepository.save(authorRequest);
    }

    @Override
    public void rejectRequest(Long id) {
        AuthorRequest authorRequest = findById(id);

        if (authorRequest.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Author request is not in PENDING status");
        }

        authorRequest.setStatus(RequestStatus.REJECTED);
        authorRequestRepository.save(authorRequest);
    }
}
