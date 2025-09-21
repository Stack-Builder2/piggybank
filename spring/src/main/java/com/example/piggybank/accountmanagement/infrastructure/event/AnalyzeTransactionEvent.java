package com.example.piggybank.accountmanagement.infrastructure.event;

import java.util.UUID;
import lombok.Getter;

@Getter
public class AnalyzeTransactionEvent extends TransactionEvent {

    private final UUID transactionId;

    public AnalyzeTransactionEvent(Object source, String description, UUID transactionId) {
        super(source, description);
        this.transactionId = transactionId;
    }
}
