package com.project.playlist.domain.playlist.presentation.dto.request;

import com.project.playlist.domain.playlist.PlayList;
import com.project.playlist.domain.playlist.enums.Category;
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

    public PlayList toEntity(PlayList playList) {
        return PlayList.builder()
                .id(playList.getId())
                .musicName(musicName)
                .musicURL(musicURL)
                .musicContent(musicContent)
                .category(category)
                .date(playList.getDate())
                .member(playList.getMember())
                .comments(playList.getComments())
                .build();
    }

}
