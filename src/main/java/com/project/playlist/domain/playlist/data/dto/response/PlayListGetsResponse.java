package com.project.playlist.domain.playlist.data.dto.response;


import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PlayListGetsResponse {
    private Long id;
    private String studentId;
    private String studentName;
    private String musicName;
    private String musicURL;
    private String musicContent;
    private Category category;


    public PlayListGetsResponse(Long id, Member member, String musicName, String musicURL, String musicContent, Category category ) {
        this.id = id;
        this.studentId = member.getStudentId();
        this.studentName = member.getStudentName();
        this.musicName = musicName;
        this.musicURL = musicURL;
        this.musicContent = musicContent;
        this.category = category;
    }
}
