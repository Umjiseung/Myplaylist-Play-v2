package com.project.playlist.domain.member.controller;

import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{studentId}")
    public ResponseEntity<MemberResponse> findMemberInfo(@PathVariable String studentId) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.myMemberInfo(studentId));
    }

    @GetMapping
    public ResponseEntity<List<PlayListGetsResponse>> getMyPlaylist() {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMyPlaylist());
    }

    @GetMapping("/my-playlist")
    public ResponseEntity<List<PlayListGetsResponse>> getMyPlaylistCategory(@RequestParam Category category) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMyPlaylistCategory(category));
    }

}
