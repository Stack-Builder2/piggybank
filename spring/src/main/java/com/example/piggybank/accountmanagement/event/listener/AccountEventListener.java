package com.example.piggybank.accountmanagement.event.listener;

import com.example.piggybank.accountmanagement.domain.service.facade.AccountFacadeService;
import com.example.piggybank.global.codef.event.CodefIdCreatedEvent;
import com.example.piggybank.global.codef.event.CodefTranHistoryCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEventListener {

    private final AccountFacadeService accountService;

    @EventListener
    public void handleCodefIdCreatedListener(CodefIdCreatedEvent event) {
        accountService.setConnectedId(event.getAccount().getAccountId().toString(), event.getAccount().getUserId().toString(), event.getConnectedId());
    }

    @EventListener
    public void handleAccountUpdateListener(CodefTranHistoryCreatedEvent event) {
        accountService.updateBalance(event.getAccountId() ,event.getBalance());
        accountService.updateConsumption(event.getAccountId());
    }

}