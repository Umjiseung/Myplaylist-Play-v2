package com.project.playlist.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PLAYLISTBOARD_NOT_FOUND(HttpStatus.NOT_FOUND,""),
    USERNAME_DUPLICATED(HttpStatus.CONFLICT,""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"");

    private HttpStatus httpStatus;
    private String message;
}
