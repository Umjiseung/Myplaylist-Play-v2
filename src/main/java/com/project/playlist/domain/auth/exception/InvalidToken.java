package com.project.playlist.domain.auth.exception;

import com.project.playlist.global.error.exception.ErrorCode;
import com.project.playlist.global.error.exception.GlobalException;

public class InvalidToken extends GlobalException {
    public InvalidToken() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
