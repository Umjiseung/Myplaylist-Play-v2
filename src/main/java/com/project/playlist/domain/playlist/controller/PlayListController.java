package com.project.playlist.domain.playlist.controller;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.global.user.CustomUserDetailsService;
import com.project.playlist.domain.playlist.data.dto.request.PlayListDeleteRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListUpdateRequest;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListInfoResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListUpdateResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListWriteResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
import com.project.playlist.domain.playlist.service.PlayListService;
import com.project.playlist.global.user.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlayListController {

    private final PlayListService playListService;

    // Playlist 작성
    @PostMapping("/write")
    public ResponseEntity<PlayListWriteResponse> musicWrite(@AuthenticationPrincipal UserDetailsService userDetailsService, @RequestBody PlayListWriteRequest writeRequest) {
        playListService.playListWrite(userDetailsService.getMember(),writeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    // 카테고리별 Playlist 가져오기
    @GetMapping("/{category}")
    public ResponseEntity<List<PlayListGetsResponse>> musicOfGets(@PathVariable("category") Category category, Member member) {
        return new ResponseEntity<>(playListService.playListOfGets(member,category),HttpStatus.OK);
    }

    // playlist 상세보기
    @GetMapping("/{category}/{id}")
    public ResponseEntity<PlayListInfoResponse> musicGet(@PathVariable("category") Category category,@PathVariable("id") Long id,Member member) {
        return new ResponseEntity<>(playListService.playListGet(id,member),HttpStatus.OK);
    }

    // Playlist 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> musicDelete(@AuthenticationPrincipal UserDetailsService userDetailsService,@PathVariable("id") Long id, @RequestBody PlayListDeleteRequest deleteRequest) {
        playListService.playListDelete(id,userDetailsService.getMember(), deleteRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // playlist 수정하기
    @PatchMapping("/{id}")
    public ResponseEntity<PlayListUpdateResponse> musicUpdate(@AuthenticationPrincipal UserDetailsService userDetailsService,@PathVariable("id") Long id, @RequestBody PlayListUpdateRequest updateRequest) {
        playListService.playListUpdate(id,userDetailsService.getMember(), updateRequest);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }



}
