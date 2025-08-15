package com.example.piggybank.domain.profile.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ProfileReqDto(
    @NotNull
    UUID userId
) {

}
