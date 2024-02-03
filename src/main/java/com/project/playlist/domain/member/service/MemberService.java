package com.project.playlist.domain.member.service;

import com.project.playlist.domain.member.data.dto.request.UpdatePasswordRequest;
import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;

import java.util.List;


public interface MemberService {
    MemberResponse myMemberInfo(String StudentId);
    List<MyPlaylistGetsResponse> getMyPlaylist();
    void updatePassword(String studentId, UpdatePasswordRequest request);
}
