package com.project.playlist.domain.user.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "table_user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_username")
    private String userName;

    @Column(name = "user_password")
    private String password;
    
    @Column(name = "user_studentid")
    private String studentID;


    

}
