package com.project.playlist.domain.member.data.entity;


import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Id;

@RedisHash(value = "refreshToken", timeToLive = 60)
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
