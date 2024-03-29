package com.project.playlist.domain.playlist.presentation.dto.response;


import com.project.playlist.domain.playlist.enums.Category;
import com.project.playlist.domain.playlist.PlayList;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor
public class PlayListGetsResponse {
    private UUID id;
    private String studentId;
    private String studentName;
    private String musicName;
    private String musicURL;
    private String musicContent;
    private Category category;
    private String date;


    public PlayListGetsResponse(PlayList playList) {
        this.id = playList.getId();
        this.studentId = playList.getMember().getStudentId();
        this.studentName = playList.getMember().getStudentName();
        this.musicName = playList.getMusicName();
        this.musicURL = playList.getMusicURL();
        this.musicContent = playList.getMusicContent();
        this.category = playList.getCategory();
        this.date = playList.getDate();
    }
}
