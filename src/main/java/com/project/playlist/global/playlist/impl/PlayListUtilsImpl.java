package com.project.playlist.global.playlist.impl;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
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
    @Override
    public List<MyPlaylistGetsResponse> findPlaylistsByUserInfo(Member member) {
        List<PlayList> playLists = playListRepository.findBoardsByMember(member);
        return getBoardTitle(playLists);
    }

    @Override
    public void validate(Member userInfo, PlayList boardInfo) {
        boolean checkBoardOwner = playListRepository.existsByMemberAndMusicName(userInfo,boardInfo.getMusicName());
        if(!checkBoardOwner) throw new IllegalArgumentException("유효한 플리가 없습니다.");
    }

    private List<MyPlaylistGetsResponse> getBoardTitle(List<PlayList> boardList){
        List<MyPlaylistGetsResponse> list = boardList.stream().map(playList->
                        new MyPlaylistGetsResponse(playList.getId (),playList.getMusicName(),playList.getMusicContent(),playList.getMusicURL()))
                .collect(Collectors.toList());
        return list;
    }
}
