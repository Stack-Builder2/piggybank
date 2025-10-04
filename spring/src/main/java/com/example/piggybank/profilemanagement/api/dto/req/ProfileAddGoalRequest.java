package com.example.piggybank.profilemanagement.api.dto.req;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProfileAddGoalRequest(
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 18, fraction = 2)
    BigDecimal goal
) {

}
