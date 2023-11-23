package com.project.playlist.domain.member.data.dto;

import com.project.playlist.domain.member.data.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String email;
    private String studentId;
    private String studentName;
    private String password;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail(),member.getStudentId(), member.getStudentName(),member.getPassword());
    }
}
