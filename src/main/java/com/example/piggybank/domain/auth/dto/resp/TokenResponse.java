package com.example.piggybank.domain.auth.dto.resp;

public record TokenResponse(
    String accessToken,
    String tokenType,
    String email
) {

}
