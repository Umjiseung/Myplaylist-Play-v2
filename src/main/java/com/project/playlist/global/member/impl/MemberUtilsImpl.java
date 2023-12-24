package com.project.playlist.global.member.impl;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.exception.MemberNotFoundException;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.global.member.MemberDetailsImpl;
import com.project.playlist.global.member.MemberUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberUtilsImpl implements MemberUtils {
    private final MemberRepository memberRepository;

    @Override
    public Member getCurrentMember() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(id);
        return memberRepository.findById(Long.valueOf(id))
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean checkExistName(String studentName) {
        return memberRepository.existsByStudentName(studentName);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

}
