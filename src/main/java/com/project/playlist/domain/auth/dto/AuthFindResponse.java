package com.project.playlist.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthFindResponse {
    private String password;

    public static AuthFindResponse of(String password) {
        return new AuthFindResponse(password);
    }
}
