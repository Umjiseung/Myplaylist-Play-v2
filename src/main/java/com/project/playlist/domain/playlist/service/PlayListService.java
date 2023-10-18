package com.project.playlist.domain.playlist.service;


import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListWriteResponse;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayListService {

    private PlayListRepository playListRepository;

    @Transactional
    public ResponseEntity<PlayListWriteResponse> playListWrite(PlayListWriteRequest writeRequest) {
        if (writeRequest.getMusicName().isBlank() || writeRequest.getMusicURL().isBlank()) throw new IllegalArgumentException("필수항목이 비어있습니다.");
        return
    }

}
