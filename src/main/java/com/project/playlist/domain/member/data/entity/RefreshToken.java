package com.project.playlist.domain.member.data.entity;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "refreshToken", timeToLive = 60)
@Builder
@Getter
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
