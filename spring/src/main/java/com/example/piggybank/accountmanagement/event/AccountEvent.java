package com.example.piggybank.accountmanagement.event;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public abstract class AccountEvent extends ApplicationEvent {
    
    private final Account account;
    private final String eventType;
    
    public AccountEvent(Object source, Account account, String eventType) {
        super(source);
        this.account = account;
        this.eventType = eventType;
    }
}
