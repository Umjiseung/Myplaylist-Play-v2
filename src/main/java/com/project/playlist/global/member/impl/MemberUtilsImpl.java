package com.project.playlist.global.member.impl;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.global.member.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUtilsImpl implements MemberUtils {
    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        return getMemberByEmail(email);
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    public void checkExistName(String studentName) {
        memberRepository.existsByStudentName(studentName);
    }

    public void checkExistEmail(String email) {
        memberRepository.existsByEmail(email);
    }

}
