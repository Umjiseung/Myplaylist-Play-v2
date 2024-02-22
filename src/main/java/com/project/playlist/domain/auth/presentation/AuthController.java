package com.project.playlist.domain.auth.presentation;

import com.project.playlist.domain.auth.dto.*;
import com.project.playlist.domain.auth.presentation.dto.LoginRequest;
import com.project.playlist.domain.auth.presentation.dto.SignUpRequest;
import com.project.playlist.domain.auth.presentation.dto.TokenDto;
import com.project.playlist.domain.auth.service.AuthService;

import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@Valid @RequestBody SignUpRequest requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(requestDto));
    }

    @PatchMapping
    public ResponseEntity<TokenDto> refresh(@RequestHeader String refreshToken) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refresh(refreshToken));
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader String refreshToken){
        authService.logout(refreshToken);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
