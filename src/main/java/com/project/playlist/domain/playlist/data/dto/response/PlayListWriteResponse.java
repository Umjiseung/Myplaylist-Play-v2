package com.project.playlist.domain.playlist.data.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@Setter
public class PlayListWriteResponse {
    private Long id;
    private String studentId;
    private String studentName;
    private String musicName;
    private String musicURL;
    private String musicCategory;

    public PlayListWriteResponse(Long id, String studentId, String studentName, String musicName, String musicURL, String musicCategory) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.musicName = musicName;
        this.musicURL = musicURL;
        this.musicCategory = musicCategory;
    }
}
