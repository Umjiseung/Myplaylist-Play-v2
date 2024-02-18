package com.project.playlist.domain.playlist.exception;

import com.project.playlist.global.error.exception.ErrorCode;
import com.project.playlist.global.error.exception.GlobalException;

public class PlaylistNotFoundException extends GlobalException {
    public PlaylistNotFoundException() {
        super(ErrorCode.PLAYLIST_NOT_FOUND);
    }
}
