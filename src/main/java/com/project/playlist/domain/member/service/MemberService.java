package com.project.playlist.domain.member.service;

import com.project.playlist.domain.member.data.dto.MemberResponseDto;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;

import java.util.List;

public interface MemberService {
    MemberResponseDto myMemberInfo(String StudentId);
    List<MyPlaylistGetsResponse> getMyPlaylist(Member member);
}
