package com.project.playlist.domain.auth.controller;

import com.project.playlist.domain.auth.dto.*;
import com.project.playlist.domain.auth.service.AuthService;

import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;

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
        MemberResponse result = authService.signup(requestDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest requestDto) {
        TokenDto result = authService.login(requestDto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping
    public ResponseEntity<TokenDto> refresh(@RequestHeader String refreshToken) {
        TokenDto result = authService.refresh(refreshToken);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader String refreshToken){
        authService.logout(refreshToken);
        return ResponseEntity.ok().build();
    }
}
