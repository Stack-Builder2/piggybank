package com.example.piggybank.global.external.service;

import com.example.piggybank.global.external.dto.resp.BatchResponse;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;

public interface PatternAnalysisService {

    public BatchResponse analyzeDescriptions(List<String[]> descriptions);

}
