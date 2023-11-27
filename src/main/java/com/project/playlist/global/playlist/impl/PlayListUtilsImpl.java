package com.project.playlist.global.playlist.impl;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistInfoResponse;
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
    public List<MyPlaylistInfoResponse> findBoardsByUserInfo(Member member) {
        List<PlayList> boardList = playListRepository.findBoardsByUser(member);
        return getBoardTitle(boardList);
    }

    @Override
    public void validate(Member userInfo, PlayList boardInfo) {
        boolean checkBoardOwner = playListRepository.existsByUserAndTitle(userInfo,boardInfo.getMusicName());
        if(!checkBoardOwner) throw new IllegalArgumentException("유효한 플리가 없습니다.");
    }

    private List<MyPlaylistInfoResponse> getBoardTitle(List<PlayList> boardList){
        List<MyPlaylistInfoResponse> list = boardList.stream().map(playList->
                        new MyPlaylistInfoResponse(playList.getId (),playList.getMusicName(),playList.getMusicContent(),playList.getMusicURL()))
                .collect(Collectors.toList());
        return list;
    }
}
