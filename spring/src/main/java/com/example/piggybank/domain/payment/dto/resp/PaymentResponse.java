package com.example.piggybank.domain.payment.dto.resp;

import java.util.UUID;

public record PaymentResponse(
    UUID paymentId,
    UUID accountId,
    Long categoryId,
    String description
) {

}
