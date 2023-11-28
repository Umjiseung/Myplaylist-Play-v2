package com.project.playlist.global.playlist;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
import com.project.playlist.domain.playlist.data.entity.PlayList;

import java.util.List;

public interface PlayListUtils {

    List<MyPlaylistGetsResponse> findPlaylistsByUserInfo(Member userInfo);

    void validate(Member member, PlayList playList);
}
