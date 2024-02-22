package com.project.playlist.domain.member.service;

import com.project.playlist.domain.member.presentation.dto.response.MemberResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.entity.enums.Category;

import java.util.List;


public interface MemberService {
    MemberResponse myMemberInfo(String StudentId);
    List<PlayListGetsResponse> getMyPlaylist();
    List<PlayListGetsResponse> getMyPlaylistCategory(Category category);
}
