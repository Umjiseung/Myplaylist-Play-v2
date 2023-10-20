package com.project.playlist.domain.playlist.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayListInfoResponse {
    private Long id;

    private String studentId;

    private String studentName;

    private String musicName;

    private String musicURL;

    private String musicCategory;

}
