package com.project.playlist.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    PLAYLIST_NOT_FOUND(404, "Playlist Not Found"),
    ALREADY_EXIST_MEMBER(400, "Already Exist Member"),
    PASSWORD_MISMATCH(400, "Password Mismatch"),
    NOT_THE_OWNER(400, "Not The Owner"),
    EXPIRED_TOKEN(401, "Expired Token"),
    EXPIRED_REFRESHTOKEN(401, "Expired RefreshToken"),
    INVALID_TOKEN(401, "Invalid Token"),
    UNAUTHORIZED(401, "Unauthorized"),
    MEMBER_NOT_SAME(401,"Member Not Same"),
    MEMBER_NOT_FOUND(404, "Member Not Found");


    private final int status;
    private final String message;
}