package com.project.playlist.domain.auth.service;

import com.project.playlist.domain.auth.dto.TokenDto;
import com.project.playlist.domain.auth.dto.TokenRequestDto;
import com.project.playlist.domain.auth.dto.SignUpRequest;
import com.project.playlist.domain.member.data.dto.response.MemberResponse;

public interface AuthService {

    MemberResponse signup(SignUpRequest signUpRequest);
    TokenDto login(SignUpRequest signUpRequest);
    TokenDto refresh(TokenRequestDto tokenRequestDto);
    void logout();
}
