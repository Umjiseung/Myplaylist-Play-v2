package com.project.playlist.domain.member.data.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;


@RedisHash(value = "refreshToken", timeToLive = 60)
@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String key;

    private String value;

    public void ExpireValue(String value) {
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }



}
