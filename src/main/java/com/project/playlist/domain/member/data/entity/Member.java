package com.project.playlist.domain.member.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.playlist.domain.member.data.dto.MemberRequestDto;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import javax.persistence.*;
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
    private Long id;

    private String studentId;

    private String studentName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "rt_key")
    private String key;

    @Column(name = "rt_value")
    private String value;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    List<PlayList> playList = new ArrayList<>();

    public Member(String email ,String studentId, String studentName, String password, Authority authority) {
        this.email = email;
        this.studentId = studentId;
        this.studentName = studentName;
        this.password = password;
        this.authority = authority;
    }

    public Member(MemberRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.studentId = requestDto.getStudentId();
        this.studentName = requestDto.getStudentName();
        this.password = requestDto.getPassword();
    }
    public Member updateValue(String refreshToken) {
        this.value = refreshToken;
        return this;
    }
    public void updateRefreshToken(String refreshToken){
        this.value = refreshToken;
    }
}

