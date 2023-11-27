package com.project.playlist.domain.playlist.service.impl;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import com.project.playlist.domain.playlist.service.PlayListService;
import com.project.playlist.global.member.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayListServiceImpl implements PlayListService {

    private final PlayListRepository playListRepository;
    private final MemberUtils memberUtils;


    @Override
    @Transactional
    public void playListWrite(PlayListWriteRequest writeRequest) {
        Member userInfo = memberUtils.getCurrentMember();
        PlayList playList = writeRequest.toEntity(userInfo);
        playListRepository.save(playList);

    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayListGetsResponse> playListOfGets(Member member,Category category) {
        List<PlayList> playLists = playListRepository.findByCategory(category);
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

    @Override
    @Transactional(readOnly = true)
    public PlayListInfoResponse playListGet(Long id,Member member) {
        PlayList playList = playListRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id에 해당하는 PlayList를 찾지 못 했습니다"));
        return new PlayListInfoResponse(
                playList.getId(),
                playList.getMember(),
                playList.getMusicName(),
                playList.getMusicURL(),
                playList.getMusicContent(),
                playList.getCategory()
        );
    }

    @Override
    @Transactional
    public void playListDelete(Long id) {
        PlayList playList = playListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 해당하는 PlayList를 삭제할 수 없습니다. " + id));


        playListRepository.delete(playList);
    }

    @Override
    @Transactional
    public void playListUpdate(Long id, PlayListUpdateRequest updateRequest) {
        PlayList playList = playListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 해당하는 PlayList를 찾을 수 없습니다: " + id));


        playList.update(updateRequest.getMusicName(), updateRequest.getMusicURL(), updateRequest.getMusicContent(), updateRequest.getCategory());
        playListRepository.save(playList);
    }

}
