package com.project.playlist.domain.member.service.impl;

import com.project.playlist.domain.member.data.dto.request.UpdatePassword;
import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.exception.MemberNotFoundException;
import com.project.playlist.domain.member.exception.MemberNotSameException;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
import com.project.playlist.global.member.MemberUtils;
import com.project.playlist.global.playlist.PlayListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PlayListUtils playListUtils;
    private final MemberUtils memberUtils;

    @Transactional(rollbackFor = {Exception.class},readOnly = true)
    public MemberResponse myMemberInfo(String StudentId) {
        return memberRepository.findByStudentId(StudentId)
                .map(MemberResponse::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Transactional(rollbackFor = {Exception.class},readOnly = true)
    public List<MyPlaylistGetsResponse> getMyPlaylist() {
        Member member = memberUtils.getCurrentMember();
        return playListUtils.findPlaylistsByUserInfo(member);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void updatePassword(String studentId, UpdatePassword request) {
        Member member = memberUtils.getCurrentMember();
        Member findMember = memberRepository.findByStudentId(studentId)
                .orElseThrow(MemberNotFoundException::new);

        if (!Objects.equals(member.getId(), findMember.getId()))
            throw new MemberNotSameException();

        member.UpdateMember(request);
    }

}
