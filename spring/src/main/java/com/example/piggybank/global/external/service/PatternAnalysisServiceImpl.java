package com.example.piggybank.global.external.service;

import com.example.piggybank.global.external.dto.req.BatchRequest;
import com.example.piggybank.global.external.dto.req.TxnRequest;
import com.example.piggybank.global.external.dto.resp.BatchResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PatternAnalysisServiceImpl implements PatternAnalysisService{

    private final WebClient pythonClient;

    @Override
    public BatchResponse analyzeDescriptions(List<TxnRequest> items) {
        BatchRequest request = new BatchRequest(items);
        return pythonClient.post()
            .uri("/analyze")
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(BatchResponse.class)
            .block();
    }
}
