package com.project.playlist.domain.auth.service;

import com.project.playlist.domain.auth.dto.*;
import com.project.playlist.domain.member.data.dto.response.MemberResponse;

public interface AuthService {

    MemberResponse signup(SignUpRequest signUpRequest);
    TokenDto login(LoginRequest signUpRequest);
    TokenDto refresh(TokenRequestDto tokenRequestDto);
    void logout();

}
