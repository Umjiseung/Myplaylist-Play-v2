package com.project.playlist.domain.member.service;

import com.project.playlist.domain.member.data.dto.MemberResponseDto;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.repository.MemberRepository;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.global.playlist.PlayListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PlayListUtils playListUtils;

    public MemberResponseDto myMemberInfo(String studentName) {
        return memberRepository.findByStudentName(studentName)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    public List<MyPlaylistGetsResponse> getMyPlaylist(Member member) {
        List<MyPlaylistGetsResponse> myPlaylists = playListUtils.findPlaylistsByUserInfo(member);
        return myPlaylists;
    }

}
