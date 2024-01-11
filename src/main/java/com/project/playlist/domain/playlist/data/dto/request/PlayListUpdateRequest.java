package com.project.playlist.domain.playlist.data.dto.request;

import com.project.playlist.domain.playlist.data.entity.Category;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
public class PlayListUpdateRequest {

    @NotBlank
    private String musicName;
    @NotBlank
    private String musicURL;
    @NotBlank
    private String musicContent;
    @NotNull
    private Category category;
}
