package com.project.playlist.domain.playlist.data.entity;


import com.project.playlist.domain.member.data.entity.Member;
import com.project.playlist.domain.playlist.data.dto.request.PlayListWriteRequest;
import javax.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayList{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String musicName;

    @Column(nullable = false)
    private String musicURL;

    @Column(nullable = false)
    private String musicContent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    public PlayList(Member member, PlayListWriteRequest writeRequest) {
        this.musicName = writeRequest.getMusicName();
        this.musicURL = writeRequest.getMusicURL();
        this.musicContent = writeRequest.getMusicContent();
        this.category = writeRequest.getCategory();
        this.member = member;
    }


    public void update(String musicName, String musicURL, String musicContent, Category category) {
        this.musicName = musicName;
        this.musicURL = musicURL;
        this.musicContent = musicContent;
        this.category = category;
    }
}
