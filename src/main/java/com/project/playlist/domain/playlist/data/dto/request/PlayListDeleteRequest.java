package com.project.playlist.domain.playlist.data.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PlayListDeleteRequest {
    @NotBlank
    private String playListPW;
}
