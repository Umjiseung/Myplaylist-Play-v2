package com.project.playlist.domain.playlist.service;


import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListWriteResponse;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayListService {

    private final PlayListRepository playListRepository;

    @Transactional
    public PlayListWriteResponse playListWrite(PlayListWriteRequest writeRequest) {
        if (writeRequest.getStudentName().isBlank() || writeRequest.getStudentId().isBlank()) throw new IllegalArgumentException("인적사항이 비어있습니다.");
        if (writeRequest.getMusicName().isBlank() || writeRequest.getMusicURL().isBlank()) throw new IllegalArgumentException("필수항목이 비어있습니다.");
        PlayList song = PlayList.builder()
                .id(writeRequest.getId())
                .studentId(writeRequest.getStudentId())
                .studentName(writeRequest.getStudentName())
                .musicName(writeRequest.getMusicName())
                .musicURL(writeRequest.getMusicURL())
                .musicCategory(writeRequest.getMusicCategory())
                .build();
        song = playListRepository.save(song);
        return new PlayListWriteResponse(song.getId(),song.getStudentId(),song.getStudentName(),song.getMusicName(),song.getMusicURL(),song.getMusicCategory());

    }

    @Transactional(readOnly = true)
    public List<PlayListGetsResponse> playListGets() {
        List<PlayList> playLists = playListRepository.findAll();
        List<PlayListGetsResponse> responseLists = new ArrayList<>();

        for (PlayList playList : playLists) {
            responseLists.add(new PlayListGetsResponse(playList.getId(),playList.getStudentId(),playList.getStudentName(),playList.getMusicName(),playList.getMusicURL(),playList.getMusicCategory()));
        }

        return responseLists;
    }

    @Transactional(readOnly = true)
    public PlayListInfoResponse playListGet(Long id) {
        PlayList playList = playListRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new PlayListInfoResponse(playList.getId(),playList.getStudentId(),playList.getStudentName(),playList.getMusicName(),playList.getMusicURL(),playList.getMusicCategory());
    }

    @Transactional
    public void playListDelete(Long id) {
        PlayList playList = playListRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        playListRepository.delete(playList);
    }

}
