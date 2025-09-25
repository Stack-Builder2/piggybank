package com.example.piggybank.accountmanagement.event;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.Getter;

@Getter
public class TestEvent extends TransactionEvent {
    
    private final CompletableFuture<String> replyFuture;
    
    public TestEvent(Object source, UUID userId,
        Account account, String eventType) {
        super(source, userId, account, eventType);
        this.replyFuture = new CompletableFuture<>();
    }
}
