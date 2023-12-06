package com.project.playlist.domain.member.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.playlist.domain.auth.dto.SignUpRequest;
import com.project.playlist.domain.playlist.data.entity.PlayList;
import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    @Column(nullable = false)
    List<PlayList> playList = new ArrayList<>();

    public Member(String email ,String studentId, String studentName, String password, Authority authority) {
        this.email = email;
        this.studentId = studentId;
        this.studentName = studentName;
        this.password = password;
        this.authority = authority;
    }

    public Member(SignUpRequest requestDto) {
        this.email = requestDto.getEmail();
        this.studentId = requestDto.getStudentId();
        this.studentName = requestDto.getStudentName();
        this.password = requestDto.getPassword();
    }

    public void update(Member member) {
        this.id = member.getId();
        this.studentId = member.getStudentId();
        this.studentName = member.getStudentName();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.authority = member.getAuthority();

    }
}



