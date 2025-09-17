package com.example.piggybank.accountmanagement.api.dto.request;

public record TransactionCreateRequest (
    String accountId,
    String startDate,
    String endDate
){

}
