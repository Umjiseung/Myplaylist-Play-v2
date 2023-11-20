package com.project.playlist.domain.playlist.data.dto.response;

import com.project.playlist.domain.playlist.data.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PlayListUpdateResponse {
    private Long id;
    private String studentId;
    private String studentName;
    private String musicName;
    private String musicURL;
    private String musicContent;
    private Category category;

    public PlayListUpdateResponse(Long id, String studentId, String studentName, String musicName, String musicURL, String musicContent, Category category ) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.musicName = musicName;
        this.musicURL = musicURL;
        this.musicContent = musicContent;
        this.category = category;
    }
}
