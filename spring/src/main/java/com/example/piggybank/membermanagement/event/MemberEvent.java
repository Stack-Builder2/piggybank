package com.example.piggybank.membermanagement.event;

import java.util.UUID;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public abstract class MemberEvent extends ApplicationEvent {
    private final UUID userId;
    private final String eventType;
    
    public MemberEvent(Object source, UUID userId, String eventType) {
        super(source);
        this.userId = userId;
        this.eventType = eventType;
    }
}
