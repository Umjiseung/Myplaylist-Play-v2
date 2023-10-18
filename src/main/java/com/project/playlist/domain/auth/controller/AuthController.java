package com.project.playlist.domain.auth.controller;

import com.project.playlist.domain.auth.data.dto.request.JoinRequest;
import com.project.playlist.domain.auth.data.dto.request.LoginRequest;
import com.project.playlist.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest joinRequest) {
        authService.join(joinRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<> login(@RequestBody LoginRequest loginRequest) {

    }

    @PutMapping("")
    public ResponseEntity<Void> logout() {
        authService.logout
    }


}
