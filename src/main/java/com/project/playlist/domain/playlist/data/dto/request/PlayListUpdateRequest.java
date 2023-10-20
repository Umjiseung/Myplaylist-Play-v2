package com.project.playlist.domain.playlist.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayListUpdateRequest {
    private Long id;
    private String studentId;
    private String StudentName;
    private String musicName;
    private String musicURL;
    private String musicCategory;
}
