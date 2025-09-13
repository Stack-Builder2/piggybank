package com.example.piggybank.accountmanagement.api.dto.request;

public record AccountUpdateRequest(

        String accountNum,
        String bankName
) {

}