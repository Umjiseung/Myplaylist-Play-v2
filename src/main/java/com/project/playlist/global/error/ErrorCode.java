package com.project.playlist.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    PLAYLIST_NOT_FOUND(404,"Playlist Not Found"),
    ALREADY_EXIST_EMAIL(400,"Already Exist Email"),
    PASSWORD_MISMATCH(400,"PasswordMismatch"),
    ALREADY_EXIST_STUDENTNAME(400,"Already Exist StudentName"),
    NOT_THE_OWNER(400,"Not The Owner"),
    EXPIRED_TOKEN(401,"Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),
    UNAUTHORIZED(401, "Unauthorized"),
    MEMBER_NOT_FOUND(404,"Member Not Found");

    private final int status;
    private final String message;
}