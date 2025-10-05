package com.example.piggybank.accountmanagement.event.listener;

import com.example.piggybank.accountmanagement.domain.service.facade.AccountFacadeService;
import com.example.piggybank.profilemanagement.event.LimitCompareRequestEvent;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompareLimitEventListener {
    
    private final AccountFacadeService accountFacadeService;
    
    @EventListener
    public void compareLimit(LimitCompareRequestEvent event){
        BigDecimal limit = event.getProfile().getLimit();
        String result = accountFacadeService.compareConsumption(event.getProfile().getUserId(), limit);
        event.getReplyFuture().complete(result);
    }
}
