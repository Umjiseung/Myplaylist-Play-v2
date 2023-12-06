package com.project.playlist.global.member;

import com.project.playlist.domain.member.data.entity.Member;

public interface MemberUtils {

    Member getCurrentMember();
    Member getMemberByEmail(String email);
    boolean checkExistName(String studentName);
    boolean checkExistEmail(String email);
}
