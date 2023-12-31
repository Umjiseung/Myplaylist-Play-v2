package com.project.playlist.domain.member.controller;

import com.project.playlist.domain.member.data.dto.request.UpdatePassword;
import com.project.playlist.domain.member.data.dto.response.MemberResponse;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
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
    public ResponseEntity<MemberResponse> findMemberInfoById(@PathVariable String studentId) {
        return ResponseEntity.ok(memberService.myMemberInfo(studentId));
    }

    @GetMapping
    public ResponseEntity<List<MyPlaylistGetsResponse>> getMyPlaylist() {
        return ResponseEntity.ok(memberService.getMyPlaylist());
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<Void> updateMemberInfo(
            @PathVariable String studentId,
            @RequestBody UpdatePassword request
    ) {
        memberService.updatePassword(studentId,request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
