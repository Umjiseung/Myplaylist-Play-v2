package com.project.playlist.domain.member.controller;

import com.project.playlist.domain.member.data.dto.MemberResponseDto;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/mypage")
    public ResponseEntity<MemberResponseDto> findMemberInfoById() {
        return ResponseEntity.ok(memberService.findMemberInfoById(SecurityUtil.getCurrentMemberId()));
    }
}
