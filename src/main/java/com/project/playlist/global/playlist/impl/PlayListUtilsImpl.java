package com.project.playlist.global.playlist.impl;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import com.project.playlist.global.playlist.PlayListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayListUtilsImpl implements PlayListUtils {
    private final PlayListRepository playListRepository;

    public List<PlayListGetsResponse> findPlaylistsByUserInfo(Member member) {
        List<PlayList> playLists = playListRepository.findPlayListsByMember(member);
        return getBoardTitle(playLists);
    }

    public void validate(Member userInfo, PlayList playlistInfo) {
        boolean checkBoardOwner = playListRepository.existsByMemberAndMusicName(userInfo,playlistInfo.getMusicName());
        if(!checkBoardOwner) throw new IllegalArgumentException("유효한 플리가 없습니다.");
    }

    private List<PlayListGetsResponse> getBoardTitle(List<PlayList> boardList){
        return boardList.stream().map(PlayListGetsResponse::new)
                .collect(Collectors.toList());
    }
}
