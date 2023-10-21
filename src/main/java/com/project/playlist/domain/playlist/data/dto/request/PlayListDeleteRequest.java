package com.project.playlist.domain.playlist.data.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayListDeleteRequest {

    private final String playListPW;

}
