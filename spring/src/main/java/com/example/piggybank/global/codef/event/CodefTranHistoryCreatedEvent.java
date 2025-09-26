package com.example.piggybank.global.codef.event;

import com.example.piggybank.accountmanagement.api.dto.response.FromCodefResponse;
import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto.TranHistory;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.Getter;

@Getter
public class CodefTranHistoryCreatedEvent extends CodefEvent{
    
    private final CompletableFuture<FromCodefResponse> replyFuture;
    private final List<TranHistory> histories;
    private final UUID accountId;
    private final String balance;
    
    public CodefTranHistoryCreatedEvent(Object source, List<TranHistory> histories,
        UUID accountId, String balance) {
        super(source);
        this.histories = histories;
        this.accountId = accountId;
        this.balance = balance;
        replyFuture = new CompletableFuture<>();
    }
}
