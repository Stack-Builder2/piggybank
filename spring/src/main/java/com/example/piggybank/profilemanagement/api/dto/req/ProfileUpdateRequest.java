package com.example.piggybank.profilemanagement.api.dto.req;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record ProfileUpdateRequest(

    @NotNull
    UUID userId,

    BigDecimal goal,

    BigDecimal limit

) {

}
