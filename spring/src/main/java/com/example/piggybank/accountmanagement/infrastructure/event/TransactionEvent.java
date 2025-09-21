package com.example.piggybank.accountmanagement.infrastructure.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class TransactionEvent extends ApplicationEvent {

    private final String description;

    public TransactionEvent(Object source, String description) {
        super(source);
        this.description = description;
    }
}
