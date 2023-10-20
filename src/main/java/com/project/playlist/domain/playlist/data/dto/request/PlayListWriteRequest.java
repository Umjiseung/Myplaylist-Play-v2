package com.project.playlist.domain.playlist.data.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayListWriteRequest {
    private final Long id;
    private final String studentId;
    private final String studentName;
    private final String musicName;
    private final String musicURL;
    private final String musicCategory;

}
