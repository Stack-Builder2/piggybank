package com.example.piggybank.membermanagement.infrastructure.event;

import java.time.Clock;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class EmailEvent extends ApplicationEvent {

    private final String to;
    private final String subject;
    private final String verificationUrl;

    public EmailEvent(Object source, String email, String subject, String verificationUrl) {
        super(source);
        to = email;
        this.subject = subject;
        this.verificationUrl = verificationUrl;
    }

}
