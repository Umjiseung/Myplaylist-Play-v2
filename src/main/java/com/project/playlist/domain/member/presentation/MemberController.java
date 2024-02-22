package com.project.playlist.domain.member.presentation;

import com.project.playlist.domain.member.presentation.dto.response.MemberResponse;
import com.project.playlist.domain.member.service.MemberService;
import com.project.playlist.domain.playlist.presentation.dto.response.PlayListGetsResponse;
import com.project.playlist.domain.playlist.enums.Category;
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

    @GetMapping("/{student_id}")
    public ResponseEntity<MemberResponse> findMemberInfo(@PathVariable("student_id") String studentId) {
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
