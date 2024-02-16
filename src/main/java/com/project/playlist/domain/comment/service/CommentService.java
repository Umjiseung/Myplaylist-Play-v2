package com.project.playlist.domain.comment.service;

import com.project.playlist.domain.comment.data.dto.request.UpdateCommentRequestDto;
import com.project.playlist.domain.comment.data.dto.request.WriteCommentRequestDto;

import java.util.UUID;

public interface CommentService {
    void writeComment(UUID id, WriteCommentRequestDto writeCommentRequestDto);
    void updateComment(UUID commentId, UpdateCommentRequestDto updateCommentRequestDto);
    void deleteComment(UUID commentId);
}
