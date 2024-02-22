package com.project.playlist.domain.auth.service.impl;

import com.project.playlist.domain.auth.exception.AlreadyExistMemberException;
import com.project.playlist.domain.auth.exception.ExpiredTokenException;
import com.project.playlist.domain.auth.exception.PasswordNotMatchException;
import com.project.playlist.domain.auth.presentation.dto.LoginRequest;
import com.project.playlist.domain.auth.presentation.dto.SignUpRequest;
import com.project.playlist.domain.auth.presentation.dto.TokenDto;
import com.project.playlist.domain.auth.service.AuthService;
import com.project.playlist.domain.member.presentation.dto.response.MemberResponse;
import com.project.playlist.domain.member.Member;
import com.project.playlist.domain.member.RefreshToken;
import com.project.playlist.domain.member.exception.MemberNotFoundException;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.domain.member.repository.RefreshTokenRepository;
import com.project.playlist.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class AuthServiceImpl implements AuthService {
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public MemberResponse signup(SignUpRequest signUpRequest) {
        if (memberRepository.existsByStudentIdAndEmail(signUpRequest.getStudentId(), signUpRequest.getEmail())) {
            throw new AlreadyExistMemberException();
        }
        Member member = signUpRequest.toMember(passwordEncoder);
        return MemberResponse.of(memberRepository.save(member));
    }

    public TokenDto login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new PasswordNotMatchException();
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(member.getId(), member.getAuthority());

        saveRefreshToken(tokenDto, member);

        return tokenDto;
    }

    public TokenDto refresh(String refreshToken) {
        String parseRefreshToken = tokenProvider.parseRefreshToken(refreshToken);

        RefreshToken validRefreshToken = refreshTokenRepository.findById(parseRefreshToken)
                .orElseThrow(ExpiredTokenException::new);

        Member member = memberRepository.findById(validRefreshToken.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        TokenDto tokenDto = tokenProvider.generateTokenDto(member.getId(), member.getAuthority());

        saveRefreshToken(tokenDto, member);
        refreshTokenRepository.deleteById(validRefreshToken.getRefreshToken());

        return tokenDto;
    }

    public void logout(String refreshToken) {
        String parseRefreshToken = tokenProvider.parseRefreshToken(refreshToken);

        RefreshToken validRefreshToken = refreshTokenRepository.findById(parseRefreshToken)
                .orElseThrow(ExpiredTokenException::new);

        refreshTokenRepository.deleteById(validRefreshToken.getRefreshToken());
    }

    private void saveRefreshToken(TokenDto tokenDto, Member member) {
        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(tokenDto.getRefreshToken())
                .memberId(member.getId())
                .expiredAt(tokenDto.getRefreshTokenExpiresIn())
                .build();

        refreshTokenRepository.save(refreshToken);
    }
}
