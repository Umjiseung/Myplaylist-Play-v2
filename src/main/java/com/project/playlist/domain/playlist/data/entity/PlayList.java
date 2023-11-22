package com.project.playlist.domain.playlist.data.entity;


import com.project.playlist.domain.member.data.entity.Member;
import jakarta.persistence.*;
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
    private String studentId;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String musicName;

    @Column(nullable = false)
    private String musicURL;

    @Column(nullable = false)
    private String musicContent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String playListPW;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member user;


    public void update(String studentId, String studentName, String musicName, String musicURL, String musicContent, Category category) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.musicName = musicName;
        this.musicURL = musicURL;
        this.musicContent = musicContent;
        this.category = category;
    }
}
