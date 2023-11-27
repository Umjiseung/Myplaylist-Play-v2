package com.project.playlist.domain.playlist.data.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPlaylistInfoResponse {
    private Long id;
    private String musicName;
    private String musicURL;
    private String musicContent;

    public MyPlaylistInfoResponse(Long id, String musicName, String musicURL, String musicContent) {
        this.id = id;
        this.musicName = musicName;
        this.musicURL = musicURL;
        this.musicContent = musicContent;
    }
}
