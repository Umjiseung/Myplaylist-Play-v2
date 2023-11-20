package com.project.playlist.domain.playlist.data.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PlayListDeleteRequest {
    @NotBlank
    private String playListPW;
}
