package com.example.piggybank.accountmanagement.api.dto.request;

import java.util.UUID;

public record TransactionRequest(
    UUID transactionId,
    String description
) {

}
