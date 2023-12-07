package com.project.playlist.domain.auth.exception;

import com.project.playlist.global.error.exception.ErrorCode;
import com.project.playlist.global.error.exception.GlobalException;

public class ExpiredRefreshToken extends GlobalException {
    public ExpiredRefreshToken() {
        super(ErrorCode.EXPIRED_REFRESHTOKEN);
    }
}
