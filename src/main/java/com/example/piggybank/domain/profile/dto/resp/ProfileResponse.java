package com.example.piggybank.domain.profile.dto.resp;

import java.util.UUID;

public record ProfileResponse(
    UUID profileId,
    String password,
    String ph
) {

}
