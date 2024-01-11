package com.project.playlist.domain.auth.dto;

import com.project.playlist.domain.member.data.entity.Authority;
import com.project.playlist.domain.member.data.entity.Member;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String studentId;
    @NotBlank
    private String studentName;
    @NotBlank
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .studentId(studentId)
                .studentName(studentName)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }
}
