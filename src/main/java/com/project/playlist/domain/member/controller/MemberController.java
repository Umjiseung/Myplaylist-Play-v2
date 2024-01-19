package com.project.playlist.domain.member.controller;

import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
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
    public ResponseEntity<List<MyPlaylistGetsResponse>> getMyPlaylist() {
        List<MyPlaylistGetsResponse> result = memberService.getMyPlaylist();
        return ResponseEntity.ok(result);
    }

}
