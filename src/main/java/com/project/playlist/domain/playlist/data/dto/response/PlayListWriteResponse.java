package com.project.playlist.domain.playlist.data.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayListWriteResponse {
    private final Long id;
    private final String studentId;
    private final String musicName;
    private final String musicURL;
    private final String musicCategory;
    private final String studentName;

}
