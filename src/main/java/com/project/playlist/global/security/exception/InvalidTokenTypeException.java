package com.project.playlist.global.security.exception;

import com.project.playlist.global.error.exception.ErrorCode;
import com.project.playlist.global.error.exception.GlobalException;

public class InvalidTokenTypeException extends GlobalException {
    public InvalidTokenTypeException() {
        super(ErrorCode.INVALID_TOKEN_TYPE);
    }
}
