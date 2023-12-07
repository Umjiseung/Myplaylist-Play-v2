package com.project.playlist.domain.member.controller;

import com.project.playlist.domain.member.data.dto.MemberResponseDto;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.member.service.impl.MemberServiceImpl;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{studentId}")
    public ResponseEntity<MemberResponseDto> findMemberInfoById(@PathVariable String studentId) {
        return ResponseEntity.ok(memberService.myMemberInfo(studentId));
    }

    @GetMapping("/my-playlist")
    public ResponseEntity<List<MyPlaylistGetsResponse>> getMyPlaylist() {
        return ResponseEntity.ok(memberService.getMyPlaylist());
    }
}
