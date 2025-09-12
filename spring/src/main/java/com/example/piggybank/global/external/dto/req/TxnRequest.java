package com.refactoring.piggybank.global.external.dto.req;

// 분석할 거래 1건
public record TxnRequest(
    String id,
    String description
) {
}
