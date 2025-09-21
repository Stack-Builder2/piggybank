package com.example.piggybank.membermanagement.event;

import java.util.UUID;
import lombok.Getter;

@Getter
public class MemberCreatedEvent extends MemberEvent{
    
    public MemberCreatedEvent(Object source, UUID userId, String eventType) {
        super(source, userId, eventType);
    }
}
