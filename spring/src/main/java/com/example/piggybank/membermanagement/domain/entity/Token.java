package com.example.piggybank.membermanagement.domain.entity;

import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("token")
public class Token {

    @Id
    private String tokenId;

    private String email;

    @TimeToLive
    private Long ttlSeconds;

    public static Token create(String token, String email, Duration ttl) {
        Token t = new Token();
        t.tokenId = token;
        t.email = email;
        t.ttlSeconds = ttl.toSeconds();
        return t;
    }

}
