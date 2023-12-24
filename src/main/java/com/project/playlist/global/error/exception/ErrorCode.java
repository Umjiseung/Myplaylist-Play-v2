package com.project.playlist.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    PLAYLIST_NOT_FOUND(404, "Playlist Not Found"),            // 재생 목록을 찾을 수 없음 (HTTP 404)
    ALREADY_EXIST_EMAIL(400, "Already Exist Email"),          // 이미 존재하는 이메일 (HTTP 400)
    ALREADY_EXIST_MEMBER(400, "Already Exist Member"),        // 이미 존재하는 회원 (HTTP 400)
    PASSWORD_MISMATCH(400, "Password Mismatch"),              // 비밀번호 불일치 (HTTP 400)
    ALREADY_EXIST_STUDENTNAME(400, "Already Exist StudentName"), // 이미 존재하는 학생 이름 (HTTP 400)
    NOT_THE_OWNER(400, "Not The Owner"),                      // 소유자가 아님 (HTTP 400)
    EXPIRED_TOKEN(401, "Expired Token"),                      // 토큰 만료 (HTTP 401)
    EXPIRED_REFRESHTOKEN(401, "Expired RefreshToken"),        // 리프레시 토큰 만료 (HTTP 401)
    INVALID_TOKEN(401, "Invalid Token"),                      // 잘못된 토큰 (HTTP 401)
    UNAUTHORIZED(401, "Unauthorized"),                        // 권한 없음 (HTTP 401)
    MEMBER_NOT_FOUND(404, "Member Not Found");                // 회원을 찾을 수 없음 (HTTP 404)


    private final int status;
    private final String message;
}