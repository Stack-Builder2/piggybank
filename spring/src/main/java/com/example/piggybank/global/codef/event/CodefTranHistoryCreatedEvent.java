package com.example.piggybank.global.codef.event;

import com.example.piggybank.global.codef.dto.res.CodefTransactionResDto.TranHistory;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CodefTranHistoryCreatedEvent extends CodefEvent{
    
    private final List<TranHistory> histories;
    private final UUID accountId;
    
    public CodefTranHistoryCreatedEvent(Object source, List<TranHistory> histories,
        UUID accountId) {
        super(source);
        this.histories = histories;
        this.accountId = accountId;
    }
}
