package com.project.playlist.domain.member.controller;

import com.project.playlist.domain.member.data.dto.MemberResponseDto;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.playlist.data.dto.response.MyPlaylistGetsResponse;
import com.project.playlist.domain.playlist.data.dto.response.PlayListGetsResponse;
import com.project.playlist.global.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{studentName}")
    public ResponseEntity<MemberResponseDto> findMemberInfoById(@PathVariable String studentName) {
        return ResponseEntity.ok(memberService.myMemberInfo(studentName));
    }

    @GetMapping("/{studentName}/myplaylist")
    public ResponseEntity<MyPlaylistGetsResponse> getMyPlaylist(@PathVariable String studentName, Member member) {
        return ResponseEntity.ok(memberService.getMyPlaylist(member));
    }
}
