package com.project.playlist.domain.auth.service;

import com.project.playlist.domain.auth.dto.TokenDto;
import com.project.playlist.domain.auth.dto.TokenRequestDto;
import com.project.playlist.domain.member.data.dto.MemberRequestDto;
import com.project.playlist.domain.member.data.dto.MemberResponseDto;

public interface AuthService {

    MemberResponseDto signup(MemberRequestDto memberRequestDto);
    TokenDto login(MemberRequestDto memberRequestDto);
    TokenDto refresh(TokenRequestDto tokenRequestDto);
    void logout();
}
