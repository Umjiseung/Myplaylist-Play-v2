package com.project.playlist.domain.playlist.service;


import com.project.playlist.domain.playlist.data.dto.request.PlayListDeleteRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListUpdateResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListWriteResponse;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                .musicContent(writeRequest.getMusicContent())
                .musicCategory(writeRequest.getMusicCategory())
                .playListPW(writeRequest.getPlayListPW())
                .build();
        song = playListRepository.save(song);
        return new PlayListWriteResponse(song.getId(),song.getStudentId(),song.getStudentName(),song.getMusicName(),song.getMusicURL(),song.getMusicContent(),song.getMusicCategory());

    }

    @Transactional(readOnly = true)
    public List<PlayListGetsResponse> playListGets() {
        List<PlayList> playLists = playListRepository.findAll();
        List<PlayListGetsResponse> responseLists = new ArrayList<>();

        for (PlayList playList : playLists) {
            responseLists.add(new PlayListGetsResponse(playList.getId(),playList.getStudentId(),playList.getStudentName(),playList.getMusicName(),playList.getMusicURL(),playList.getMusicContent(),playList.getMusicCategory()));
        }

        return responseLists;
    }

    @Transactional(readOnly = true)
    public PlayListInfoResponse playListGet(Long id) {
        PlayList playList = playListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 해당하는 PlayList가 존재하지 않습니다. 해당 id: " + id));
        return new PlayListInfoResponse(playList.getId(),playList.getStudentId(),playList.getStudentName(),playList.getMusicName(),playList.getMusicURL(),playList.getMusicContent(),playList.getMusicCategory());
    }

    @Transactional
    public void playListDelete(Long id, PlayListDeleteRequest deleteRequest) {
        PlayList playList = playListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 해당하는 PlayList를 삭제할 수 없습니다. " + id));

        if (!Objects.equals(playList.getPlayListPW(), deleteRequest.getPlayListPW())) throw new IllegalArgumentException("등록된 비밀번호와 일치하지 않습니다. 입력한 비밀번호: " + deleteRequest.getPlayListPW());

        playListRepository.delete(playList);
    }

    @Transactional
    public void playListUpdate(Long id,PlayListUpdateRequest updateRequest) {
        PlayList playList = playListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 해당하는 PlayList를 찾을 수 없습니다: " + id));

        if (!Objects.equals(playList.getPlayListPW(), updateRequest.getPlayListPW())) throw new IllegalArgumentException("등록된 비밀번호와 일치하지 않습니다. 입력한 비밀번호: " + updateRequest.getPlayListPW());

        playList.setStudentId(updateRequest.getStudentId());
        playList.setStudentName(updateRequest.getStudentName());
        playList.setMusicName(updateRequest.getMusicName());
        playList.setMusicURL(updateRequest.getMusicURL());
        playList.setMusicContent(updateRequest.getMusicContent());
        playList.setMusicCategory(updateRequest.getMusicCategory());
    }

}
