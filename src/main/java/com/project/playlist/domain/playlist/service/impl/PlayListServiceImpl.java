package com.project.playlist.domain.playlist.service.impl;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.exception.PlaylistNotFound;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import com.project.playlist.domain.playlist.service.PlayListService;
import com.project.playlist.global.member.MemberUtils;
import com.project.playlist.global.playlist.PlayListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PlayListServiceImpl implements PlayListService {

    private final PlayListRepository playListRepository;
    private final MemberUtils memberUtils;
    private final PlayListUtils playListUtils;


    public void playListWrite(PlayListWriteRequest writeRequest) {
        Member userInfo = memberUtils.getCurrentMember();
        PlayList playList = writeRequest.toEntity(userInfo);
        playListRepository.save(playList);
    }

    @Transactional(readOnly = true)
    public List<PlayListGetsResponse> playlistAllGets() {
        List<PlayList> playLists = playListRepository.findAll();
        List<PlayListGetsResponse> responses = new ArrayList<>();

        for (PlayList playList: playLists) {
            responses.add(new PlayListGetsResponse(
                    playList.getId(),
                    playList.getMember(),
                    playList.getMusicName(),
                    playList.getMusicURL(),
                    playList.getMusicContent(),
                    playList.getCategory()
            ));
        }
        return responses;
    }

    @Transactional(readOnly = true)
    public List<PlayListGetsResponse> playListOfGets(Category category) {
        List<PlayList> playLists = playListRepository.findByCategory(category);
        if (playLists == null) {
            throw new PlaylistNotFound();
        }
        List<PlayListGetsResponse> listOfCategory = new ArrayList<>();

        for (PlayList playList : playLists) {
            listOfCategory.add(new PlayListGetsResponse(
                    playList.getId(),
                    playList.getMember(),
                    playList.getMusicName(),
                    playList.getMusicURL(),
                    playList.getMusicContent(),
                    playList.getCategory()
            ));
        }
        return listOfCategory;
    }

    @Transactional(readOnly = true)
    public PlayListInfoResponse playListGet(Long id,Category category) {
        PlayList playList = playListRepository.findByCategoryAndId(category, id);
        if (playList == null) {
            throw new PlaylistNotFound();
        }
        return new PlayListInfoResponse(
                playList.getId(),
                playList.getMember(),
                playList.getMusicName(),
                playList.getMusicURL(),
                playList.getMusicContent(),
                playList.getCategory()
        );
    }

    public void playListDelete(Long id) {
        Member userInfo = memberUtils.getCurrentMember();
        PlayList playListInfo = playListRepository.findByIdOrIdNull(id);
        playListUtils.validate(userInfo,playListInfo);
        playListRepository.deleteById(id);
    }

    public void playListUpdate(Long id, PlayListUpdateRequest updateRequest) {
        Member member = memberUtils.getCurrentMember();
        PlayList playList = playListRepository.findById(id).orElseThrow(PlaylistNotFound::new);
        playListUtils.validate(member,playList);
        playList.update(updateRequest.getMusicName(), updateRequest.getMusicURL(), updateRequest.getMusicContent(), updateRequest.getCategory());
    }

}
