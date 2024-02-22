package com.project.playlist.domain.member.presentation.dto.response;

import com.project.playlist.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private String email;
    private String studentId;
    private String studentName;

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getEmail(),member.getStudentId(), member.getStudentName());
    }

}
