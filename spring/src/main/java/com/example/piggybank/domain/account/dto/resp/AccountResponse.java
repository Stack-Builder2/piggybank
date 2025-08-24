package com.example.piggybank.domain.account.dto.resp;

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
