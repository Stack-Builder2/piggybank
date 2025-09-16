package com.example.piggybank.membermanagement.domain.service;

import java.time.Duration;

public interface TokenService {

    String saveTempToken(String email, Duration ttl);
    String consume(String token);
    boolean exists(String token);

}
