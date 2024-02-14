package com.project.playlist.domain.playlist.data.dto.response;

import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class PlayListInfoResponse {
    private UUID id;
    private String studentId;
    private String studentName;
    private String musicName;
    private String musicURL;
    private String musicContent;
    private Category category;
    private String date;

    public PlayListInfoResponse(UUID id, Member member, String musicName, String musicURL, String musicContent, Category category, String date) {
        this.id = id;
        this.studentId = member.getStudentId();
        this.studentName = member.getStudentName();
        this.musicName = musicName;
        this.musicURL = musicURL;
        this.musicContent = musicContent;
        this.category = category;
        this.date = date;
    }
}
