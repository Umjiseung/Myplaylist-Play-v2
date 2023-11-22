package com.project.playlist.global.jwt;

import com.project.playlist.domain.auth.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final Key key;

    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(Authentication authentication) {
        // 권한들 가져오기
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
            .setSubject(authentication.getName())       // payload "sub": "name"
            .claim(AUTHORITIES_KEY, authorities)        // payload "auth": "ROLE_USER"
            .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시)
            .signWith(key, SignatureAlgorithm.HS512)    // header "alg": "HS512"
            .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
            .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();

        return TokenDto.builder()
            .grantType(BEARER_TYPE)
            .accessToken(accessToken)
            .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
            .refreshToken(refreshToken)
            .build();
    }

    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            // JWT를 파싱하고 유효성을 검사를 시도합니다.
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            // 성공하면 true를 반환합니다 (유효한 토큰).
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            // JWT 서명이 유효하지 않거나 형식이 잘못된 경우를 처리합니다.
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            // JWT가 만료된 경우를 처리합니다.
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            // 지원되지 않는 JWT인 경우를 처리합니다.
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            // JWT가 잘못된 구조로 생성된 경우를 처리합니다. (예: null 또는 빈 문자열)
            log.info("JWT 토큰이 잘못되었습니다.");
        }

        // 어떤 예외가 발생하더라도 false를 반환합니다 (유효하지 않은 토큰).
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            // 주어진 액세스 토큰을 파싱하여 클레임을 추출합니다.
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            // 토큰이 만료된 경우, 만료된 토큰에서 클레임을 추출하여 반환합니다.
            return e.getClaims();
        }
    }

}
