package com.example.piggybank.membermanagement.domain.service;

import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final StringRedisTemplate redisTemplate;
    private static final String PREFIX = "password_reset:";

    @Override
    public String saveTempToken(String email, Duration ttl) {

        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(PREFIX + token, email, ttl);
        return token;
    }

    @Override
    public String get(String token) {
        return redisTemplate.opsForValue().get(PREFIX + token);
    }

    @Override
    public String consume(String token) {
        String key = PREFIX + token;
        String email = redisTemplate.opsForValue().get(key);
        if (email != null) {
            redisTemplate.delete(key); // 1회성 사용
        }
        return email;
    }
}
