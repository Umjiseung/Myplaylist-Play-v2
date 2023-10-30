package com.project.playlist.domain.playlist.controller;

import com.project.playlist.domain.playlist.data.dto.request.PlayListDeleteRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListUpdateResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListWriteResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
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


    // 카테고리별 Playlist 가져오기
    @GetMapping("/{category}")
    public ResponseEntity<List<PlayListGetsResponse>> musicOfGets(@RequestParam Category category) {
        return new ResponseEntity<>(playListService.playListOfGets(category),HttpStatus.OK);
    }

    // playlist 상세보기
    @GetMapping("/{category}/{id}")
    public ResponseEntity<PlayListInfoResponse> musicGet(@RequestParam Category category,@PathVariable("id") Long id) {
        return new ResponseEntity<>(playListService.playListGet(category,id),HttpStatus.OK);
    }

    // Playlist 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> musicDelete(@PathVariable("id") Long id, @RequestBody PlayListDeleteRequest deleteRequest) {
        playListService.playListDelete(id, deleteRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayListUpdateResponse> musicUpdate(@PathVariable("id") Long id, @RequestBody PlayListUpdateRequest updateRequest) {
        playListService.playListUpdate(id, updateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
