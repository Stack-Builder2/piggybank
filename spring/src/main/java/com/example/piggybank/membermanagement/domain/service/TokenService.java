package com.refactoring.piggybank.membermanagement.domain.service;

import java.time.Duration;

public interface TokenService {

    String saveTempToken(String email, Duration ttl);
    String get(String token);
    String consume(String token);

}
