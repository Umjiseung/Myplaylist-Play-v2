package com.project.playlist.domain.member.service.impl;

import com.project.playlist.domain.member.data.dto.request.UpdatePasswordRequest;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PlayListUtils playListUtils;
    private final MemberUtils memberUtils;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MemberResponse myMemberInfo(String studentId) {
        Member user = memberUtils.getCurrentMember();
        Member userInfo = memberRepository.findByStudentId(studentId)
                .orElseThrow(MemberNotFoundException::new);

        if (user != userInfo) {
            throw new MemberNotSameException();
        }

        return MemberResponse.of(user);
    }

    @Transactional(readOnly = true)
    public List<MyPlaylistGetsResponse> getMyPlaylist() {
        Member member = memberUtils.getCurrentMember();
        return playListUtils.findPlaylistsByUserInfo(member);
    }

    public void updatePassword(String studentId, UpdatePasswordRequest request) {
        Member member = memberUtils.getCurrentMember();
        Member findMember = memberRepository.findByStudentId(studentId)
                .orElseThrow(MemberNotFoundException::new);

        if (member != findMember) {
            throw new MemberNotSameException();
        }

        request.updatePassword(passwordEncoder, member.getPassword());

        member.UpdateMember(request);
    }

}