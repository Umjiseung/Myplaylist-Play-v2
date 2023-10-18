package com.project.playlist.domain.auth.data.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

    private String userName;
    private String password;

}
