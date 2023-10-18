package com.project.playlist.domain.auth.service;

import com.project.playlist.domain.auth.data.dto.request.JoinRequest;
import com.project.playlist.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<Void> join(JoinRequest joinRequest) {

    }

    @Transactional
    public ResponseEntity<Void> login() {

    }

    @Transactional
    public ResponseEntity<Void> logout() {

    }


}
