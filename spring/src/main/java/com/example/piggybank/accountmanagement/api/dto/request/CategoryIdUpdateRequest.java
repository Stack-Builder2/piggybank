package com.example.piggybank.accountmanagement.api.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CategoryIdUpdateRequest(
    @NotNull
    UUID transactionId,

    @NotNull
    Long categoryId
) {

}
