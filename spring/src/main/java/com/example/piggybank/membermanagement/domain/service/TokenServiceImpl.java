package com.example.piggybank.membermanagement.domain.service;

import com.example.piggybank.membermanagement.domain.entity.Token;
import com.example.piggybank.membermanagement.infrastructure.repository.TokenRepository;
import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    public String saveTempToken(String email, Duration ttl) {

        String token = UUID.randomUUID().toString();
        tokenRepository.save(Token.create(token, email, ttl));
        return token;
    }

    @Override
    public boolean exists(String token) {
        return tokenRepository.existsById(token);
    }

    @Override
    public String consume(String token) {
        return tokenRepository.findById(token)
            .map(t -> {
                tokenRepository.deleteById(token);
                return t.getEmail();
            })
            .orElse(null);
    }


}
