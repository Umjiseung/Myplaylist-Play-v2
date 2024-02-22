package com.project.playlist.domain.member;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;


@RedisHash(value = "refreshToken", timeToLive = 60)
@Builder
@Getter
public class RefreshToken {

    @Id
    private String refreshToken;
    private UUID memberId;
    private Long expiredAt;
}
