package com.project.playlist.domain.comment.data.dto.request;

import com.project.playlist.domain.comment.data.entiry.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
public class UpdateCommentRequestDto {
    @NotBlank
    private String comment;

    public Comment toEntity(Comment comment) {
        return Comment.builder()
                .id(comment.getId())
                .member(comment.getMember())
                .playlist(comment.getPlaylist())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .comment(this.comment)
                .build();
    }
}
