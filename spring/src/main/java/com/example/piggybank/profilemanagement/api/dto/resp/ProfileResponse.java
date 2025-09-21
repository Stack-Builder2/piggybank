package com.example.piggybank.profilemanagement.api.dto.resp;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record ProfileResponse(
    @NotNull
    UUID userId,
    BigDecimal goal,
    BigDecimal limit
) {

}
