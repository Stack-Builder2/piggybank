package com.example.piggybank.global.codef.event.listener;

import com.example.piggybank.accountmanagement.event.AccountCreatedEvent;
import com.example.piggybank.global.codef.dto.CodefConnectedIdReqDto;
import com.example.piggybank.global.codef.service.CodefService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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

    
    
    
    private String createConnectedId(AccountCreatedEvent event) {
        String accessToken = codefService.publishCodefAccessToken();
        return null;
    }
    
}
