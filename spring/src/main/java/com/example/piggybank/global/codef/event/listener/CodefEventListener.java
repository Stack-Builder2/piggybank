package com.example.piggybank.global.codef.event.listener;

import com.example.piggybank.accountmanagement.event.AccountCreatedEvent;
import com.example.piggybank.accountmanagement.event.TransactionCreateEvent;
import com.example.piggybank.global.codef.dto.req.CodefConnectedIdReqDto;
import com.example.piggybank.global.codef.dto.req.CodefTransactionReqDto;
import com.example.piggybank.global.codef.service.CodefService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CodefEventListener {

    private final CodefService codefService;
    
    @EventListener
    public void handleAccountCreatedEvent(AccountCreatedEvent event) {
        String accessToken = codefService.publishCodefAccessToken();
        CodefConnectedIdReqDto reqDto = new CodefConnectedIdReqDto(accessToken, event.getRequest().bankId(), event.getRequest().bankPassword(), event.getAccount());
        codefService.publishCodefConnectedId(reqDto);
    }
    
    @EventListener
    public void handleTransactionCreateEvent(TransactionCreateEvent event) {
        String accessToken = codefService.publishCodefAccessToken();
        
        CodefTransactionReqDto request = new CodefTransactionReqDto(
            accessToken,
            event.getAccount().getConnectedId(),
            event.getAccount().getAccountId(),
            event.getAccount().getBankName(),
            event.getAccount().getAccountNum(),
            event.getStartDate(),
            event.getEndDate()
        );
        
        log.info("Received transaction created event");
        
        codefService.getCodefTransactions(request);
    }
    
}
