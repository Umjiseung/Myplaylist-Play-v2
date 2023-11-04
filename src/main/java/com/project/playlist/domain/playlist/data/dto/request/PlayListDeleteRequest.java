package com.project.playlist.domain.playlist.data.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
public class PlayListDeleteRequest {
    @NotNull
    private String playListPW;
}
