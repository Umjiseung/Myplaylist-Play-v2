package com.project.playlist.domain.playlist.service.impl;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.exception.PlaylistNotFoundException;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import com.project.playlist.domain.playlist.service.PlayListService;
import com.project.playlist.global.member.MemberUtils;
import com.project.playlist.global.playlist.PlayListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PlayListServiceImpl implements PlayListService {

    private final PlayListRepository playListRepository;
    private final MemberUtils memberUtils;
    private final PlayListUtils playListUtils;


    public void playListWrite(PlayListWriteRequest writeRequest) {
        Member userInfo = memberUtils.getCurrentMember();
        SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyyMMdd");
        Date time = new Date();
        String result = dateFormat.format(time);

        PlayList playList = writeRequest.toEntity(userInfo);
        playList.insertDate(result);
        playListRepository.save(playList);
    }

    @Transactional(readOnly = true)
    public List<PlayListGetsResponse> playlistAllGets() {
        List<PlayList> playLists = playListRepository.findAll();
        return playlistGets(playLists);
    }

    @Transactional(readOnly = true)
    public List<PlayListGetsResponse> playListOfGets(Category category) {
        List<PlayList> playLists = playListRepository.findByCategory(category);
        if (playLists == null) {
            throw new PlaylistNotFoundException();
        }
        return playlistGets(playLists);
    }

    @Transactional(readOnly = true)
    public PlayListInfoResponse playListGet(UUID id) {
        PlayList playList = playListRepository.findByIdOrIdNull(id);
        if (playList == null) {
            throw new PlaylistNotFoundException();
        }

        return new PlayListInfoResponse(playList);
    }

    public void playListDelete(UUID id) {
        Member userInfo = memberUtils.getCurrentMember();
        PlayList playListInfo = playListRepository.findByIdOrIdNull(id);
        playListUtils.validate(userInfo,playListInfo);
        playListRepository.deleteById(id);
    }

    public void playListUpdate(UUID id, PlayListUpdateRequest updateRequest) {
        Member member = memberUtils.getCurrentMember();
        PlayList playList = playListRepository.findByIdOrIdNull(id);
        playListUtils.validate(member,playList);
        playList.update(updateRequest.getMusicName(), updateRequest.getMusicURL(), updateRequest.getMusicContent(), updateRequest.getCategory());
    }

    private List<PlayListGetsResponse> playlistGets(List<PlayList> playLists) {
        List<PlayListGetsResponse> responses = new ArrayList<>();

        for (PlayList playList: playLists) {
            responses.add(new PlayListGetsResponse(playList));
        }
        return responses;
    }
}
