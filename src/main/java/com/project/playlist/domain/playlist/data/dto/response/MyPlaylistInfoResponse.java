package com.project.playlist.domain.playlist.data.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPlaylistInfoResponse {
    private String musicName;
    private String musicURL;
    private String musicContent;
}
