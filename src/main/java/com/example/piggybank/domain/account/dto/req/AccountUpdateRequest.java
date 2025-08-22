package com.example.piggybank.domain.account.dto.req;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AccountUpdateRequest(

    String accountNum,
    String bankName
) {

}
