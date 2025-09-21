package com.example.piggybank.accountmanagement.infrastructure.event.listener;

import com.example.piggybank.accountmanagement.domain.service.query.CategoryQueryService;
import com.example.piggybank.accountmanagement.infrastructure.event.AnalyzeResultEvent;
import com.example.piggybank.accountmanagement.infrastructure.event.AnalyzeTransactionEvent;
import com.example.piggybank.global.external.dto.req.TxnRequest;
import com.example.piggybank.global.external.dto.resp.BatchResponse;
import com.example.piggybank.global.external.service.PatternAnalysisService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyzeCategoryListener {

    private final PatternAnalysisService patternAnalysisService;
    private final ApplicationEventPublisher eventPublisher;
    private final CategoryQueryService categoryQueryService;

    @EventListener
    public void handleAnalyzeCategoryRequested(AnalyzeTransactionEvent event) {
        TxnRequest request = new TxnRequest(event.getTransactionId().toString(), event.getDescription());
        BatchResponse response = patternAnalysisService.analyzeDescriptions(List.of(request));

        String category = response.items().getFirst().category();
        Long categoryId = categoryQueryService.getCategoryId(category);

        eventPublisher.publishEvent(new AnalyzeResultEvent(
            this,
            event.getDescription(),
            event.getTransactionId(),
            category,
            categoryId
        ));
    }

}
