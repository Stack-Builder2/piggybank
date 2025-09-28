package com.example.piggybank.accountmanagement.api.dto.request;

public record CreateTransactionRequest(
    String accountId,
    String startDate,
    String endDate
){

}
