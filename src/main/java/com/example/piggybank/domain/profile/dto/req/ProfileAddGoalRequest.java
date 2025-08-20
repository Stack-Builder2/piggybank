package com.example.piggybank.domain.profile.dto.req;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProfileAddGoalRequest(
    @NotNull
    UUID userId,
    
    @NotNull
    BigDecimal goal
) {

}
