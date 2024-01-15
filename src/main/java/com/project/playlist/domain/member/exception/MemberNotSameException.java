package com.project.playlist.domain.member.exception;

import com.project.playlist.global.error.exception.ErrorCode;
import com.project.playlist.global.error.exception.GlobalException;

public class MemberNotSameException extends GlobalException {

    public MemberNotSameException(){super(ErrorCode.MEMBER_NOT_SAME);}
}
