package com.project.playlist.domain.playlist.utils;

import com.project.playlist.domain.member.Member;
import com.project.playlist.domain.playlist.presentation.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.PlayList;

import java.util.List;

public interface PlayListUtils {

    List<PlayListGetsResponse> findPlaylistsByUserInfo(Member userInfo);

    void validate(Member member, PlayList playList);
}
