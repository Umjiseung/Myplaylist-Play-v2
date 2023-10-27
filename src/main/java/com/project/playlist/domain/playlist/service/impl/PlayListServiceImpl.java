package com.project.playlist.domain.playlist.service.impl;

import com.project.playlist.domain.playlist.data.dto.request.PlayListDeleteRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListWriteResponse;
import com.project.playlist.domain.playlist.enums.Category;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import com.project.playlist.domain.playlist.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlayListServiceImpl implements PlayListService {

    private final PlayListRepository playListRepository;

    @Override
    @Transactional
    public PlayListWriteResponse playListWrite(PlayListWriteRequest writeRequest) {
        if (Stream.of(
                writeRequest.getStudentName(),
                writeRequest.getStudentId(),
                writeRequest.getMusicName(),
                writeRequest.getMusicURL(),
                writeRequest.getMusicCategory())
                .anyMatch(String::isBlank)) {
            throw new IllegalArgumentException("인적사항 또는 필수항목이 비어있습니다.");
        }

        PlayList playList = PlayList.builder()
                .id(writeRequest.getId())
                .studentId(writeRequest.getStudentId())
                .studentName(writeRequest.getStudentName())
                .musicName(writeRequest.getMusicName())
                .musicURL(writeRequest.getMusicURL())
                .musicContent(writeRequest.getMusicContent())
                .musicCategory(writeRequest.getMusicCategory())
                .playListPW(writeRequest.getPlayListPW())
                .build();
        playList = playListRepository.save(playList);
        return new PlayListWriteResponse(
                playList.getId(),
                playList.getStudentId(),
                playList.getStudentName(),
                playList.getMusicName(),
                playList.getMusicURL(),
                playList.getMusicContent(),
                playList.getMusicCategory()
        );

    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayListGetsResponse> playListGets() {
        List<PlayList> playLists = playListRepository.findAll();
        List<PlayListGetsResponse> responseLists = new ArrayList<>();

        for (PlayList playList : playLists) {
            responseLists.add(new PlayListGetsResponse(playList.getId(),playList.getStudentId(),playList.getStudentName(),playList.getMusicName(),playList.getMusicURL(),playList.getMusicContent(),playList.getMusicCategory()));
        }

        return responseLists;
    }

    @Override
    @Transactional(readOnly = true)
    public PlayListInfoResponse playListGet(Long id) {
        PlayList playList = playListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 해당하는 PlayList가 존재하지 않습니다. 해당 id: " + id));
        return new PlayListInfoResponse(playList.getId(),playList.getStudentId(),playList.getStudentName(),playList.getMusicName(),playList.getMusicURL(),playList.getMusicContent(),playList.getMusicCategory());
    }

    @Override
    @Transactional
    public void playListDelete(Long id, PlayListDeleteRequest deleteRequest) {
        PlayList playList = playListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 해당하는 PlayList를 삭제할 수 없습니다. " + id));

        if (!Objects.equals(playList.getPlayListPW(), deleteRequest.getPlayListPW())) throw new IllegalArgumentException("등록된 비밀번호와 일치하지 않습니다. 입력한 비밀번호: " + deleteRequest.getPlayListPW());

        playListRepository.delete(playList);
    }

    @Override
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
