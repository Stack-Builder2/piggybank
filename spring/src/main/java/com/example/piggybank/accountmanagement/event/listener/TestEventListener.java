package com.example.piggybank.accountmanagement.event.listener;

import com.example.piggybank.accountmanagement.event.TestEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TestEventListener {
    
    @EventListener
    public void handleTestEvent(TestEvent event) {
        try {
            // // 1) 비즈니스 로직 수행 (검증/조회/계산 등)
            String result = "1";                               // // 예시 결과
            
            // // 2) 결과를 발행자에게 전달 (Future 완료)
            event.getReplyFuture().complete(result);
        } catch (Exception ex) {
            // // 3) 예외를 발행자에게 전파 (발행자에서 ExecutionException으로 수신)
            event.getReplyFuture().completeExceptionally(ex);
        }
    }
}
