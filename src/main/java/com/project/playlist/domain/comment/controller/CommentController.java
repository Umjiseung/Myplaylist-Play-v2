package com.project.playlist.domain.comment.controller;

import com.project.playlist.domain.comment.data.dto.request.UpdateCommentRequestDto;
import com.project.playlist.domain.comment.data.dto.request.WriteCommentRequestDto;
import com.project.playlist.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> writeComment(@PathVariable UUID id, @Valid @RequestBody WriteCommentRequestDto requestDto) {
        commentService.writeComment(id, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable UUID commentId, @Valid @RequestBody UpdateCommentRequestDto requestDto) {
        commentService.updateComment(commentId, requestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
