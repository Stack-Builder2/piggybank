package com.example.piggybank.accountmanagement.event.listener;

import com.example.piggybank.accountmanagement.domain.service.facade.TransactionFacadeService;
import com.example.piggybank.global.codef.event.CodefTranHistoryCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionEventListener {
    private final TransactionFacadeService transactionFacadeService;
    
    @EventListener
    public void handleCodefTranHistoryCreatedEvent(CodefTranHistoryCreatedEvent event) {
        log.info("transaction history recieved to transaction domain");
        transactionFacadeService.createTransactions(event.getHistories(),event.getAccountId());
    }
}
