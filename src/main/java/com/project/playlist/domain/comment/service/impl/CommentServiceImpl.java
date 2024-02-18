package com.project.playlist.domain.comment.service.impl;

import com.project.playlist.domain.comment.data.dto.request.UpdateCommentRequestDto;
import com.project.playlist.domain.comment.data.dto.request.WriteCommentRequestDto;
import com.project.playlist.domain.comment.data.entiry.Comment;
import com.project.playlist.domain.comment.exception.CommentNotFoundException;
import com.project.playlist.domain.comment.exception.CommentNotSameException;
import com.project.playlist.domain.comment.repository.CommentRepository;
import com.project.playlist.domain.comment.service.CommentService;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import com.project.playlist.domain.playlist.repository.PlayListRepository;
import com.project.playlist.global.member.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PlayListRepository playListRepository;
    private final MemberUtils memberUtils;

    public void writeComment(UUID id, WriteCommentRequestDto request) {
        Member member = memberUtils.getCurrentMember();

        PlayList playList = playListRepository.findByIdOrIdNull(id);

        Comment comment = request.toEntity(playList, member);
        commentRepository.save(comment);
    }

    public void updateComment(UUID commentId, UpdateCommentRequestDto updateCommentRequestDto) {
        Member member = memberUtils.getCurrentMember();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if (member.getId() != comment.getMember().getId())
            throw new CommentNotSameException();

        commentRepository.save(updateCommentRequestDto.toEntity(comment));
    }

    public void deleteComment(UUID commentId) {
        Member member = memberUtils.getCurrentMember();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if (member.getId() != comment.getMember().getId())
            throw new CommentNotSameException();

        commentRepository.delete(comment);
    }
}
