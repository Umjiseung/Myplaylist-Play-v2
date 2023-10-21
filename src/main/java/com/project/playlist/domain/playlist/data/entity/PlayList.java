package com.project.playlist.domain.playlist.data.entity;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;

    private String studentName;

    private String musicName;

    private String musicURL;

    private String musicContent;

    private String musicCategory;

    private String playListPW;

}
