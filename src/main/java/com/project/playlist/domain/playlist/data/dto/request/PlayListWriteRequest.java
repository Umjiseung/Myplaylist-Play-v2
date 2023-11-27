package com.project.playlist.domain.playlist.data.dto.request;


import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.entity.Category;
import javax.validation.constraints.NotBlank;

import com.project.playlist.domain.playlist.data.entity.PlayList;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
public class PlayListWriteRequest {

    @NotBlank
    private String studentId;
    @NotBlank
    private String studentName;
    @NotBlank
    private String musicName;
    @NotBlank
    private String musicURL;
    @NotBlank
    private String musicContent;
    @NotBlank
    private Category category;
    @NotBlank
    private String playListPW;

    public PlayList toEntity(Member member) {
        return PlayList.builder().member(member).musicName(musicName).musicContent(musicContent).musicURL(musicURL).category(category).playListPW(playListPW).build();
    }

}
