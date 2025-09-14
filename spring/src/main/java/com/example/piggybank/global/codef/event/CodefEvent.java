package com.example.piggybank.global.codef.event;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public abstract class CodefEvent extends ApplicationEvent {

    public CodefEvent(final Object source) {
        super(source);
    }
    
}
