package com.project.playlist.global.member.impl;

import com.project.playlist.domain.member.Member;
import com.project.playlist.domain.member.exception.MemberNotFoundException;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.global.member.MemberUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberUtilsImpl implements MemberUtils {
    private final MemberRepository memberRepository;

    @Override
    public Member getCurrentMember() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findById(UUID.fromString(id))
                .orElseThrow(MemberNotFoundException::new);
    }


}
