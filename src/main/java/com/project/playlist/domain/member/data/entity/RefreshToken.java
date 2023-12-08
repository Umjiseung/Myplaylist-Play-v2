package com.project.playlist.domain.member.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "refreshToken", timeToLive = 60)
public class RefreshToken {

    @Id
    @Column(nullable = false,name = "rt_key")
    private String key;

    @Column(nullable = false,name = "rt_value")
    private String value;

    public void ExpireValue(String value) {
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }



}
