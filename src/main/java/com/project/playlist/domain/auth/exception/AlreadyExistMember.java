package com.project.playlist.domain.auth.exception;

import com.project.playlist.global.error.exception.ErrorCode;
import com.project.playlist.global.error.exception.GlobalException;

public class AlreadyExistMember extends GlobalException {
    public AlreadyExistMember() {
        super(ErrorCode.ALREADY_EXIST_MEMBER);
    }
}
