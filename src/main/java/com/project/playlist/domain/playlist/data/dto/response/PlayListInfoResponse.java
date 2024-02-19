package com.project.playlist.domain.playlist.data.dto.response;

import com.project.playlist.domain.comment.data.dto.response.CommentResponseDto;
import com.project.playlist.domain.playlist.data.entity.enums.Category;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PlayListInfoResponse {
    private UUID id;
    private String studentId;
    private String studentName;
    private String musicName;
    private String musicURL;
    private String musicContent;
    private Category category;
    private String date;
    private List<CommentResponseDto> comments;

    public PlayListInfoResponse(PlayList playList) {
        this.id = playList.getId();
        this.studentId = playList.getMember().getStudentId();
        this.studentName = playList.getMember().getStudentName();
        this.musicName = playList.getMusicName();
        this.musicURL = playList.getMusicURL();
        this.musicContent = playList.getMusicContent();
        this.category = playList.getCategory();
        this.date = playList.getDate();
        this.comments = playList.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
