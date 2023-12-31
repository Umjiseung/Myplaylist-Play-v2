package com.project.playlist.domain.playlist.data.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPlaylistGetsResponse {
    private Long id;
    private String studentId;
    private String studentName;
    private String musicName;
    private String musicURL;
    private String musicContent;


    public MyPlaylistGetsResponse(Long id, String studentId, String studentName, String musicName, String musicURL, String musicContent) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.musicName = musicName;
        this.musicURL = musicURL;
        this.musicContent = musicContent;
    }
}
