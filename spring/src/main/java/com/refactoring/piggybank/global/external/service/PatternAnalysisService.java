package com.refactoring.piggybank.global.external.service;

import com.refactoring.piggybank.global.external.dto.req.TxnRequest;
import com.refactoring.piggybank.global.external.dto.resp.BatchResponse;

import java.util.List;

public interface PatternAnalysisService {

    // WebClient로 Python FastAPI 호출 구현
    public BatchResponse analyzeDescriptions(List<TxnRequest> items);

}
