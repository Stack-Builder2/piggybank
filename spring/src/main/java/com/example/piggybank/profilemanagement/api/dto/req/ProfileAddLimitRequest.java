package com.example.piggybank.profilemanagement.api.dto.req;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProfileAddLimitRequest(
    @NotNull
    BigDecimal limit
) {

}
