package com.refactoring.piggybank.global.external.dto.resp;

import com.example.piggybank.domain.payment.entity.InputType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentAnalyzeResponse(
    UUID paymentId,
    UUID accountId,
    Long categoryId,     // Python 분석 결과 기반
    BigDecimal amount,
    LocalDateTime transactionDate,
    InputType inputType,
    String description,
    String categoryName // 분석 결과 카테고리명
) {
}
