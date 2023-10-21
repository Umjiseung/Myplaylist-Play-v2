package com.project.playlist.domain.playlist.data.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayListUpdateResponse {
    private final Long id;
    private final String studentId;
    private final String studentName;
    private final String musicName;
    private final String musicURL;
    private final String musicContent;
    private final String musicCategory;

}
