package com.example.piggybank.accountmanagement.api.dto.response;

import jakarta.validation.constraints.NotNull;

public record AccountUpdateResponse(

    @NotNull
    String userId,
    
    @NotNull
    String accountId,
    
    @NotNull
    String message
    
    
) {

}
