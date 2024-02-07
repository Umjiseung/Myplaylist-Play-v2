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
    private String refreshToken;
    private Long memberId;
    private Long expiredAt;
}
