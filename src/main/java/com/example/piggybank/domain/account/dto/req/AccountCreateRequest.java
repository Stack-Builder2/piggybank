package com.example.piggybank.domain.account.dto.req;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AccountCreateRequest(

    @NotNull
    String accountNum,

    @NotNull
    String bankName
) {

}
