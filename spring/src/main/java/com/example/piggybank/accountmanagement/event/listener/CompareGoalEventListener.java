package com.example.piggybank.accountmanagement.event.listener;

import com.example.piggybank.accountmanagement.domain.service.facade.AccountFacadeService;
import com.example.piggybank.profilemanagement.event.GoalCompareRequestEvent;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompareGoalEventListener {
    
    private final AccountFacadeService accountFacadeService;
    
    @EventListener
    public void compareGoal(GoalCompareRequestEvent event){
        BigDecimal goal = event.getProfile().getGoal();
        String result = accountFacadeService.compareBalance(event.getProfile().getUserId(), goal);
        event.getReplyFuture().complete(result);
    }
    
}
