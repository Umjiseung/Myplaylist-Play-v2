package com.project.playlist.domain.auth.service.impl;

import com.project.playlist.domain.auth.dto.*;
import com.project.playlist.domain.auth.exception.AlreadyExistMemberException;
import com.project.playlist.domain.auth.exception.InvalidTokenException;
import com.project.playlist.domain.auth.service.AuthService;
import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.data.entity.RefreshToken;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.domain.member.repository.RefreshTokenRepository;
import com.project.playlist.global.member.MemberUtils;
import com.project.playlist.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberUtils memberUtils;

    public MemberResponse signup(SignUpRequest signUpRequest) {
        if (memberRepository.existsByStudentId(signUpRequest.getStudentId())) {
            throw new AlreadyExistMemberException();
        }

        Member member = signUpRequest.toMember(passwordEncoder);
        return MemberResponse.of(memberRepository.save(member));
    }

    public TokenDto login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    public TokenDto refresh(TokenRequestDto tokenRequestDto) {
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new InvalidTokenException();
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findById(authentication.getName())
                .orElseThrow(()-> new NullPointerException("refreshToken is null"));


        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new InvalidTokenException();
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);


        return tokenDto;
    }

    public void logout() {
        Member member = memberUtils.getCurrentMember();
        refreshTokenRepository.deleteById(member.getStudentName());
    }
}
