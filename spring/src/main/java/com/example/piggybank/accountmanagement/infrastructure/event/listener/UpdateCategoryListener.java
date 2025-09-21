package com.example.piggybank.accountmanagement.infrastructure.event.listener;

import com.example.piggybank.accountmanagement.domain.service.facade.CategoryTransactionFacadeService;
import com.example.piggybank.accountmanagement.infrastructure.event.AnalyzeResultEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCategoryListener {

    private final CategoryTransactionFacadeService categoryTransactionFacadeService;

    @EventListener
    public void handleUpdateCategoryRequested(AnalyzeResultEvent event) {
        categoryTransactionFacadeService.updateCategory(event.getTransactionId(), event.getCategoryId());
    }
}
