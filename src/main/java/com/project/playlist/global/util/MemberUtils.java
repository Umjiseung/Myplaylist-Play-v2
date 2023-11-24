package com.project.playlist.global.util;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.rmi.AlreadyBoundException;

@RequiredArgsConstructor
public class MemberUtils {
    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getMemberByEmail(email);
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    public void checkExistName(String studentName) {
        if (memberRepository.existsByStudentName(studentName));
    }

    public void checkExistEmail(String email) {
        if(memberRepository.existsByEmail(email));
    }

}
