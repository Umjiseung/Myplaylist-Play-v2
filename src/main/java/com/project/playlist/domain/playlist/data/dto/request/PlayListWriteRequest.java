package com.project.playlist.domain.playlist.data.dto.request;


import com.project.playlist.domain.member.Member;
import com.project.playlist.domain.playlist.data.entity.enums.Category;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.project.playlist.domain.playlist.data.entity.PlayList;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
public class PlayListWriteRequest {

    @NotBlank
    private String musicName;
    @NotBlank
    private String musicURL;
    @NotBlank
    private String musicContent;
    @NotNull
    private Category category;

    public PlayList toEntity(Member member) {
        return PlayList.builder()
                .member(member)
                .musicName(musicName)
                .musicContent(musicContent)
                .musicURL(musicURL)
                .category(category)
                .build();
    }

}
