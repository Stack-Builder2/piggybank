package com.example.piggybank.accountmanagement.event;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public abstract class TransactionEvent extends ApplicationEvent {
    private final UUID userId;
    private final Account account;
    private final String eventType;
    
    public TransactionEvent(Object source, UUID userId, Account account, String eventType) {
        super(source);
        this.userId = userId;
        this.account = account;
        this.eventType = eventType;
    }
}
