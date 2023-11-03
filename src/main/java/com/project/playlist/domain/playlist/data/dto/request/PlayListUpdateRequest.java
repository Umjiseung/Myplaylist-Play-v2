package com.project.playlist.domain.playlist.data.dto.request;

import com.project.playlist.domain.playlist.data.entity.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class PlayListUpdateRequest {
    private final Long id;
    @NotBlank
    private final String studentId;
    @NotBlank
    private final String studentName;
    @NotBlank
    private final String musicName;
    @NotBlank
    private final String musicURL;
    @NotBlank
    private final String musicContent;
    @NotBlank
    private final Category category;

    private final String playListPW;
}
