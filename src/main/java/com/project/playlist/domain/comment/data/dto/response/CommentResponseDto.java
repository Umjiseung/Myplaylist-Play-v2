package com.project.playlist.domain.comment.data.dto.response;

import com.project.playlist.domain.comment.data.entiry.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private UUID id;
    private UUID playlistId;
    private String studentName;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.playlistId = comment.getPlaylist().getId();
        this.studentName = comment.getMember().getStudentName();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }


}
