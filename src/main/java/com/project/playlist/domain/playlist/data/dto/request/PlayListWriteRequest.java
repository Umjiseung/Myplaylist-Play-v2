package com.project.playlist.domain.playlist.data.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class PlayListWriteRequest {
    private Long id;
    private String musicName;
    private String musicURL;

}
