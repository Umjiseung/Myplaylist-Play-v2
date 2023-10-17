package com.project.playlist.domain.playlist.data.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayListWriteRequest {
    private Long id;
    private String musicName;
    private String musicURL;

}
