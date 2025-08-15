package com.example.piggybank.domain.profile.dto;

import java.util.UUID;

public record ProfileRespDto(
    UUID profileId,
    String password,
    String ph
) {

}
