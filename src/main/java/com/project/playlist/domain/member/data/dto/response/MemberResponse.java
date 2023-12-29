package com.project.playlist.domain.member.data.dto.response;

import com.project.playlist.domain.member.data.entity.Member;
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
