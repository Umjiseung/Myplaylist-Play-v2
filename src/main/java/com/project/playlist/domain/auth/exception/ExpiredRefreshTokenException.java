package com.project.playlist.domain.auth.exception;

import com.project.playlist.global.error.exception.ErrorCode;
import com.project.playlist.global.error.exception.GlobalException;

public class ExpiredRefreshTokenException extends GlobalException {
    public ExpiredRefreshTokenException() {
        super(ErrorCode.EXPIRED_REFRESHTOKEN);
    }
}
