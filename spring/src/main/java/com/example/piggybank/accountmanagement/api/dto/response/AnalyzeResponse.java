package com.example.piggybank.accountmanagement.api.dto.response;

import java.util.UUID;

public record AnalyzeResponse(
    UUID transactionId,
    Long categoryId
) {

}
