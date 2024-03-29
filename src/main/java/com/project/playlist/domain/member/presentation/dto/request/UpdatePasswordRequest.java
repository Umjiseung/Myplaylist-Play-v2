package com.project.playlist.domain.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z0-9])(?=.*[^A-Za-z0-9]).{8,24}$", message = "Invalid Password Pattern")
    public String password;

    public void updatePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }
}
