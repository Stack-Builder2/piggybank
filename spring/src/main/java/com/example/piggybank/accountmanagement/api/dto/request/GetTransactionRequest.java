package com.example.piggybank.accountmanagement.api.dto.request;

public record GetTransactionRequest(
    String accountId,
    String startDate,
    String endDate
) {

}
