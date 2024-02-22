package com.project.playlist.domain.auth.service;

import com.project.playlist.domain.auth.presentation.dto.LoginRequest;
import com.project.playlist.domain.auth.presentation.dto.SignUpRequest;
import com.project.playlist.domain.auth.presentation.dto.TokenDto;
import com.project.playlist.domain.member.presentation.dto.response.MemberResponse;

public interface AuthService {

    MemberResponse signup(SignUpRequest signUpRequest);
    TokenDto login(LoginRequest signUpRequest);
    TokenDto refresh(String refreshToken);
    void logout(String refreshToken);

}
