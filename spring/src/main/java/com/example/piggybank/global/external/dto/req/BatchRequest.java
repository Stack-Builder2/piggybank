package com.example.piggybank.global.external.dto.req;

import java.util.List;

// 분석할 거래 목록
public record BatchRequest(
    List<TxnRequest> items
) {

}
