package com.refactoring.piggybank.accountmanagement.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record AccountCreateRequest(

        @NotNull
        String accountNum,

        @NotNull
        String bankName
) {

}