package com.project.playlist.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // playlist
    PLAYLIST_NOT_FOUND(404, "Playlist Not Found"),

    // member
    ALREADY_EXIST_MEMBER(400, "Already Exist Member"),
    MEMBER_NOT_SAME(401,"Member Not Same"),
    MEMBER_NOT_FOUND(404, "Member Not Found"),

    // token
    NOT_THE_OWNER(400, "Not The Owner"),
    EXPIRED_TOKEN(401, "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),
    UNAUTHORIZED(401, "Unauthorized"),
    INVALID_TOKEN_TYPE(401,"Invalid Token Type"),

    // auth
    PASSWORD_NOT_MATCH(400, "Password Not Match"),

    // comment
    COMMENT_NOT_FOUND(404, "Not Found Comment"),
    COMMENT_NOT_SAME(401, "Comment_Not_Same");

    private final int status;
    private final String message;
}