package com.example.piggybank.profilemanagement.event.listener;

import com.example.piggybank.membermanagement.event.MemberCreatedEvent;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileRequest;
import com.example.piggybank.profilemanagement.api.dto.resp.ProfileResponse;
import com.example.piggybank.profilemanagement.domain.service.facade.ProfileFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProfileEventListener {
    private final ProfileFacadeService profileFacadeService;
    
    @EventListener
    public void handleMemberCreated(MemberCreatedEvent event) {
        ProfileResponse response = profileFacadeService.createProfile(new ProfileRequest(event.getUserId()));
        log.info("Created profile: {}", response);
    }
}
