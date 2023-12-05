package com.project.playlist.global.member.impl;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.global.member.MemberDetailsImpl;
import com.project.playlist.global.member.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUtilsImpl implements MemberUtils {
    private final MemberRepository memberRepository;
    private final MemberDetailsImpl memberDetails;

    @Override
    public Member getCurrentMember() {
        String email = memberDetails.getUsername();
        return getMemberByEmail(email);
    }

    @Override
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    @Override
    public void checkExistName(String studentName) {
        memberRepository.existsByStudentName(studentName);
    }

    @Override
    public void checkExistEmail(String email) {
        memberRepository.existsByEmail(email);
    }

}
