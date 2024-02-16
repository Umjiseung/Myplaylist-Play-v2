package com.project.playlist.domain.member.controller;

import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.data.entity.Category;
import lombok.RequiredArgsConstructor;
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
        MemberResponse result = memberService.myMemberInfo(studentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<PlayListGetsResponse>> getMyPlaylist() {
        List<PlayListGetsResponse> result = memberService.getMyPlaylist();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/my-playlist")
    public ResponseEntity<List<PlayListGetsResponse>> getMyPlaylistCategory(@RequestParam Category category) {
        List<PlayListGetsResponse> result = memberService.getMyPlaylistCategory(category);
        return ResponseEntity.ok(result);
    }

}
