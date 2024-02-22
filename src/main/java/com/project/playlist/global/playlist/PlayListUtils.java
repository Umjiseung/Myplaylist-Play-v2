package com.project.playlist.global.playlist;

import com.project.playlist.domain.member.Member;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.entity.PlayList;

import java.util.List;

public interface PlayListUtils {

    List<PlayListGetsResponse> findPlaylistsByUserInfo(Member userInfo);

    void validate(Member member, PlayList playList);
}
