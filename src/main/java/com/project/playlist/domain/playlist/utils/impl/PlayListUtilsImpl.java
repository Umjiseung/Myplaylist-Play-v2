package com.project.playlist.domain.playlist.utils.impl;

import com.project.playlist.domain.member.Member;
import com.project.playlist.domain.playlist.presentation.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.PlayList;
import com.project.playlist.domain.playlist.exception.PlaylistNotFoundException;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import com.project.playlist.domain.playlist.utils.PlayListUtils;
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
        if(!checkBoardOwner) throw new PlaylistNotFoundException();
    }

    private List<PlayListGetsResponse> getBoardTitle(List<PlayList> playLists){
        return playLists.stream().map(PlayListGetsResponse::new)
                .collect(Collectors.toList());
    }
}
