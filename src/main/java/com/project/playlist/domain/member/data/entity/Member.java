package com.project.playlist.domain.member.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    List<PlayList> playList = new ArrayList<>();

    public Member(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
}

