package com.example.piggybank.accountmanagement.api.dto.response;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AccountCreateResponse(
        @NotNull
        UUID userId,

        @NotNull
        Account account
) {

}