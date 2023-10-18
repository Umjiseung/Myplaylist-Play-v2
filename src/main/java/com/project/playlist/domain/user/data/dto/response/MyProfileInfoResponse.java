package com.project.playlist.domain.user.data.dto.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyProfileInfoResponse {
    private final String userName;
    private final String password;
    private final String studentID;
}
