package com.example.piggybank.membermanagement.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record ResetRequest (
    @NotNull
    String email,

    @NotNull
    String name
){

}
