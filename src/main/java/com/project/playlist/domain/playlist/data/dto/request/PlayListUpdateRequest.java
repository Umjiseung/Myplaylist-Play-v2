package com.project.playlist.domain.playlist.data.dto.request;

import com.project.playlist.domain.playlist.data.entity.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;


@Getter
@RequiredArgsConstructor
public class PlayListUpdateRequest {
    private final Long id;
    @NotNull
    private final String studentId;
    @NotNull
    private final String studentName;
    @NotNull
    private final String musicName;
    private final String musicURL;
    @NotNull
    private final String musicContent;
    @NotNull
    private final Category category;
    @NotNull
    private final String playListPW;
}
