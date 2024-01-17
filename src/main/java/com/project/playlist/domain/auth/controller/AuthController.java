package com.project.playlist.domain.auth.controller;

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
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(@Valid @RequestBody TokenRequestDto requestDto) {
        return ResponseEntity.ok(authService.refresh(requestDto));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(){
        authService.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
