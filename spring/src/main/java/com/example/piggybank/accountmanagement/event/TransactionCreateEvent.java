package com.example.piggybank.accountmanagement.event;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import java.util.UUID;
import lombok.Getter;

@Getter
public class TransactionCreateEvent extends TransactionEvent{
    
    private final String startDate;
    private final String endDate;
    
    public TransactionCreateEvent(Object source,
        UUID userId,
        Account account,
        String startDate, String endDate) {
        super(source, userId, account, "TRANSACTION_CREATE");
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
