package com.example.piggybank.accountmanagement.api.dto.request;

import java.util.UUID;

public record TransactionFastApiRequest(
    UUID transactionId,
    String description
) {

}
