package com.project.playlist.domain.playlist.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.playlist.domain.comment.data.entiry.Comment;
import com.project.playlist.domain.member.data.entity.Member;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayList{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

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
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments = new ArrayList<>();


    public void update(String musicName, String musicURL, String musicContent, Category category) {
        this.musicName = musicName;
        this.musicURL = musicURL;
        this.musicContent = musicContent;
        this.category = category;
    }

    public void insertDate(String date) {
        this.date = date;
    }
}
