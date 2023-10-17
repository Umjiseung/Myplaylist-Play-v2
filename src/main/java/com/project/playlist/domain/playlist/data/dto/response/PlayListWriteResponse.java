package com.project.playlist.domain.playlist.data.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class PlayListWriteResponse {
    private Long id;
    private String musicName;
    private String musicURL;

}
