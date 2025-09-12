package com.refactoring.piggybank.membermanagement.api.dto.response;

public record TokenResponse(
        String accessToken,
        String tokenType,
        String email
) {

}