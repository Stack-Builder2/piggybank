package com.example.piggybank.global.codef.event;

import com.example.piggybank.accountmanagement.domain.entity.Account;
import lombok.Getter;

@Getter
public class CodefIdCreatedEvent extends CodefEvent {
    
    private final String connectedId;
    private final Account account;

    public CodefIdCreatedEvent(Object source, String connectedId, Account account) {
        super(source);
        this.connectedId = connectedId;
        this.account = account;
    }
    
}
