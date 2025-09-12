package com.refactoring.piggybank.accountmanagement.api.dto.request;

public record AccountUpdateRequest(

        String accountNum,
        String bankName
) {

}