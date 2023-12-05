package com.project.playlist.global.member;

import com.project.playlist.domain.member.data.entity.Member;

public interface MemberUtils {

    Member getCurrentMember(String email);
    Member getMemberByEmail(String email);
    void checkExistName(String studentName);
    void checkExistEmail(String email);
}
