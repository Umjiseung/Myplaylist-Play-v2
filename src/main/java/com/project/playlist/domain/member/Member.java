package com.project.playlist.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.playlist.domain.auth.presentation.dto.SignUpRequest;
import com.project.playlist.domain.member.enums.Authority;
import com.project.playlist.domain.playlist.PlayList;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@gsm\\.hs\\.kr$", message = "Invalid GSM email address")
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
}



