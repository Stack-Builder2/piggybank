package com.refactoring.piggybank.global.external.dto.req;

import java.util.List;

// Python FAST API 전송용 DTO
public record PatternAnalyzeRequest(
    List<TxnRequest> txnRequests
    ) {

}
