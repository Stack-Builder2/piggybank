package com.example.piggybank.accountmanagement.api.dto.response;

import java.util.UUID;

public record TransactionResponse(
    UUID paymentId,
    UUID accountId,
    Long categoryId,
    String description) {
    
}
