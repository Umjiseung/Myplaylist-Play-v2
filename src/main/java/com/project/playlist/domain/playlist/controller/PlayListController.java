package com.project.playlist.domain.playlist.controller;

import com.project.playlist.domain.playlist.data.dto.request.PlayListDeleteRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListUpdateResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListWriteResponse;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlayListController {

    private final PlayListService playListService;

    // Playlist 작성
    @PostMapping("/write")
    public ResponseEntity<PlayListWriteResponse> musicWrite(@RequestBody PlayListWriteRequest writeRequest) {
        playListService.playListWrite(writeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 모든 Playlist 가져오기
    @GetMapping
    public ResponseEntity<List<PlayListGetsResponse>> musicGets() {
        return new ResponseEntity<>(playListService.playListGets(),HttpStatus.OK);
    }

    // playlist 상세보기
    @GetMapping("/{id}")
    public ResponseEntity<PlayListInfoResponse> musicGet(@PathVariable("id") Long id) {
        return new ResponseEntity<>(playListService.playListGet(id),HttpStatus.OK);
    }

    // Playlist 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> musicDelete(@PathVariable("id") Long id, PlayListDeleteRequest deleteRequest) {
        playListService.playListDelete(id, deleteRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayListUpdateResponse> musicUpdate(@PathVariable("id") Long id, @RequestBody PlayListUpdateRequest updateRequest) {
        playListService.playListUpdate(updateRequest,id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
