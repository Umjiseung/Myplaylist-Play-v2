package com.project.playlist.domain.comment.data.dto.request;

import com.project.playlist.domain.comment.data.entiry.Comment;
import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@NoArgsConstructor
public class WriteCommentRequestDto {
    @NotBlank
    private String comment;

    public Comment toEntity(PlayList playList, Member member) {
        return Comment.builder()
                .comment(comment)
                .playlist(playList)
                .member(member)
                .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
                .modifiedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
                .build();
    }
}
