package com.project.playlist.domain.auth.service.impl;

import com.project.playlist.domain.auth.dto.TokenDto;
import com.project.playlist.domain.auth.dto.TokenRequestDto;
import com.project.playlist.domain.auth.exception.AlreadyExistMember;
import com.project.playlist.domain.auth.exception.ExpiredRefreshToken;
import com.project.playlist.domain.auth.exception.InvalidToken;
import com.project.playlist.domain.auth.service.AuthService;
import com.project.playlist.domain.auth.dto.SignUpRequest;
import com.project.playlist.domain.member.data.dto.MemberResponseDto;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.data.entity.RefreshToken;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.domain.member.repository.RefreshTokenRepository;
import com.project.playlist.global.member.MemberUtils;
import com.project.playlist.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberUtils memberUtils;

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public MemberResponseDto signup(SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmailOrStudentIdOrStudentName(
                signUpRequest.getEmail(),
                signUpRequest.getStudentId(),
                signUpRequest.getStudentName())) {
            throw new AlreadyExistMember();
        }

        Member member = signUpRequest.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public TokenDto login(SignUpRequest signUpRequest) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = signUpRequest.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public TokenDto refresh(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new InvalidToken();
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);


        // 토큰 발급
        return tokenDto;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void logout() {
        Member member = memberUtils.getCurrentMember();


    }
}
