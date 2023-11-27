package com.project.playlist.global.playlist;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistInfoResponse;
import com.project.playlist.domain.playlist.data.entity.PlayList;

import java.util.List;

public interface PlayListUtils {

    List<MyPlaylistInfoResponse> findBoardsByUserInfo(Member userInfo);

    void validate(Member member, PlayList playList);
}
