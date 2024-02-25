package com.project.playlist.domain.playlist.presentation.dto.request;


import com.project.playlist.domain.member.Member;
import com.project.playlist.domain.playlist.enums.Category;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.project.playlist.domain.playlist.PlayList;
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

    public PlayList toEntity(Member member, String date) {
        return PlayList.builder()
                .member(member)
                .musicName(musicName)
                .musicContent(musicContent)
                .musicURL(musicURL)
                .category(category)
                .date(date)
                .build();
    }

}
