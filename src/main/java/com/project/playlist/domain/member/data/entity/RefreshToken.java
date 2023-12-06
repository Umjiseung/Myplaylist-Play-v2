package com.project.playlist.domain.member.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @Column(nullable = false,name = "rt_key")
    private String key;

    @Column(nullable = false,name = "rt_value")
    private String value;

    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }



}
