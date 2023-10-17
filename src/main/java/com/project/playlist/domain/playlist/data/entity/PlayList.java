package com.project.playlist.domain.playlist.data.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TABLE_PLAYLIST")
public class PlayList {

    @Id
    @GeneratedValue
    @Column(name = "PLAYLIST_id")
    private Long id;

    @Column(name = "PLAYLIST_name")
    private String musicName;

    @Column(name = "PLAYLIST_url")
    private String musicURL;


    @Column(name = "PLAYLIST_category")
    private String musicCategory;

}
