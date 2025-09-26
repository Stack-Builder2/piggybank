package com.example.piggybank.profilemanagement.event;

import com.example.piggybank.profilemanagement.domain.entity.Profile;
import java.util.concurrent.CompletableFuture;
import lombok.Getter;

@Getter
public class LimitCompareRequestEvent extends ProfileEvent {
    private final CompletableFuture<String> replyFuture;
    public LimitCompareRequestEvent(
        Object source, Profile profile, String event) {
        super(source, profile, event);
        replyFuture = new CompletableFuture<>();
    }
}
