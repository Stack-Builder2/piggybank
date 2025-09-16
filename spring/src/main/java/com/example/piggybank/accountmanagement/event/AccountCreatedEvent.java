package com.example.piggybank.accountmanagement.event;

import com.example.piggybank.accountmanagement.api.dto.request.AccountCreateRequest;
import com.example.piggybank.accountmanagement.domain.entity.Account;
import lombok.Getter;

@Getter
public class AccountCreatedEvent extends AccountEvent {
    
    private final AccountCreateRequest request;
    
    public AccountCreatedEvent(Object source, Account account, AccountCreateRequest accountCreateRequest) {
        super(source, account, "ACCOUNT_CREATED");
        this.request = accountCreateRequest;
    }
}
