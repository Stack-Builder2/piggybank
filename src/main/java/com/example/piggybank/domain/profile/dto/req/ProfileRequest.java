package com.example.piggybank.domain.profile.dto.req;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ProfileRequest(
    @NotNull
    UUID userId
) {

}
