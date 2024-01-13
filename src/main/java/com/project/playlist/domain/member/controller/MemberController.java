package com.project.playlist.domain.member.controller;

import com.project.playlist.domain.member.data.dto.request.UpdatePasswordRequest;
import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{studentId}")
    public ResponseEntity<MemberResponse> findMemberInfo(@PathVariable String studentId) {
        return ResponseEntity.ok(memberService.myMemberInfo(studentId));
    }

    @GetMapping
    public ResponseEntity<List<MyPlaylistGetsResponse>> getMyPlaylist() {
        return ResponseEntity.ok(memberService.getMyPlaylist());
    }

}
