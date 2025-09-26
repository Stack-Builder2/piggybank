package com.example.piggybank.profilemanagement.event;

import com.example.piggybank.profilemanagement.domain.entity.Profile;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class ProfileEvent extends ApplicationEvent {
    private final Profile profile;
    private final String event;
    
    public ProfileEvent(Object source, Profile profile, String event) {
        super(source);
        this.profile = profile;
        this.event = event;
    }
}
