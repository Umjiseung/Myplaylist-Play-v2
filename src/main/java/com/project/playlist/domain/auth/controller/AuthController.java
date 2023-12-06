package com.project.playlist.domain.auth.controller;

import com.project.playlist.domain.auth.dto.TokenDto;
import com.project.playlist.domain.auth.dto.TokenRequestDto;
import com.project.playlist.domain.auth.service.AuthService;
import com.project.playlist.domain.member.data.dto.SignUpRequest;

import com.project.playlist.domain.member.data.dto.MemberResponseDto;
import com.project.playlist.global.member.MemberUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final MemberUtils memberUtils;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody SignUpRequest requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody SignUpRequest requestDto) {
//        String validatedEmail = memberUtils.getCurrentMember(requestDto.getEmail());
        return ResponseEntity.ok(authService.login(requestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(@RequestBody TokenRequestDto requestDto) {
        return ResponseEntity.ok(authService.refresh(requestDto));
    }

    @PutMapping("/logout")
    public ResponseEntity<Void> logout(){
        authService.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
