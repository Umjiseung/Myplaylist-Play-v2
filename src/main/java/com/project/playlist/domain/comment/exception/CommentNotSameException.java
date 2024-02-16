package com.project.playlist.domain.comment.exception;

import com.project.playlist.global.error.exception.ErrorCode;
import com.project.playlist.global.error.exception.GlobalException;

public class CommentNotSameException extends GlobalException {
    public CommentNotSameException() {
        super(ErrorCode.COMMENT_NOT_SAME);
    }
}
