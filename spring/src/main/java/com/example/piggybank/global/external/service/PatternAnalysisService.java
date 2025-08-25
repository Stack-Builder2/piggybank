package com.example.piggybank.global.external.service;

import com.example.piggybank.global.external.dto.req.TxnRequest;
import com.example.piggybank.global.external.dto.resp.BatchResponse;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;

public interface PatternAnalysisService {

    // WebClient로 Python FastAPI 호출 구현
    public BatchResponse analyzeDescriptions(List<TxnRequest> items);

}
