package com.project.playlist.domain.auth.controller;

import antlr.Token;
import com.project.playlist.domain.auth.dto.*;
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
        MemberResponse result = authService.signup(requestDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest requestDto) {
        TokenDto result = authService.login(requestDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(@Valid @RequestBody TokenRequestDto requestDto) {
        TokenDto result = authService.refresh(requestDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(){
        authService.logout();
        return ResponseEntity.ok().build();
    }
}
