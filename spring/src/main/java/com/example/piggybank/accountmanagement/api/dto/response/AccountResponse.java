package com.refactoring.piggybank.accountmanagement.api.dto.response;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AccountResponse(
        @NotNull
        UUID userId,

        @NotNull
        String accountNum,

        @NotNull
        String bankName
) {

}