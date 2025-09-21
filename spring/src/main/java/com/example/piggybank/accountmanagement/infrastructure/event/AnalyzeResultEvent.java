package com.example.piggybank.accountmanagement.infrastructure.event;

import java.util.UUID;
import lombok.Getter;

@Getter
public class AnalyzeResultEvent extends TransactionEvent {

    private final UUID transactionId;
    private final String category;
    private final Long categoryId;

    public AnalyzeResultEvent(Object source, String description, UUID transactionId,
        String category, Long categoryId) {
        super(source, description);
        this.transactionId = transactionId;
        this.category = category;
        this.categoryId = categoryId;
    }
}
