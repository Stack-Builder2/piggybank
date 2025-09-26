package com.example.piggybank.profilemanagement.domain.service.facade;

import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddGoalRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddLimitRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileRequest;
import com.example.piggybank.profilemanagement.api.dto.resp.ProfileResponse;
import com.example.piggybank.profilemanagement.domain.entity.Profile;
import com.example.piggybank.profilemanagement.domain.service.command.ProfileCommandService;
import com.example.piggybank.profilemanagement.domain.service.query.ProfileQueryService;
import com.example.piggybank.profilemanagement.event.GoalCompareRequestEvent;
import com.example.piggybank.profilemanagement.event.LimitCompareRequestEvent;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileFacadeServiceImpl implements ProfileFacadeService {
    
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;
    private final ApplicationEventPublisher eventPublisher;
    
    @Override
    public Profile updateLimit(String userId, ProfileAddLimitRequest request) {
        Profile profile = profileCommandService.updateLimit(UUID.fromString(userId), request.limit());
        return profile;
    }
    
    @Override
    public Profile updateGoal(String userId, ProfileAddGoalRequest request) {
        Profile profile = profileCommandService.updateGoal(UUID.fromString(userId), request.goal());
        
        return profile;
    }

    @Override
    public ProfileResponse createProfile(ProfileRequest request) {

        profileCommandService.createProfile(request.userId());

        return new ProfileResponse(
            request.userId(),
            BigDecimal.ZERO,
            BigDecimal.ZERO
        );
    }
    
    @Override
    public String compareLimit(String userId) {
        Profile profile = profileQueryService.findByUserId(UUID.fromString(userId));
        if(profile.getLimit().compareTo(BigDecimal.ZERO) < 1) {
            return "한도 금액이 올바르지 않습니다.";
        }
        LimitCompareRequestEvent event = new LimitCompareRequestEvent(
            this,
            profile,
            "Compare Limit"
        );
        eventPublisher.publishEvent(event);
        try {
            return event.getReplyFuture().get(3, java.util.concurrent.TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new IllegalStateException("리스너 응답을 받지 못했습니다.", e);
        }
    }
    
    @Override
    public String compareGoal(String userId) {
        Profile profile = profileQueryService.findByUserId(UUID.fromString(userId));
        if(profile.getGoal() == null){
            return "목표 금액을 설정하지 않았습니다.";
        }
        if(profile.getGoal().compareTo(BigDecimal.ZERO) < 1) {
            return "목표 금액이 올바르지 않습니다.";
        }
        GoalCompareRequestEvent event = new GoalCompareRequestEvent(
            this,
            profile,
            "Compare Goal"
        );
        eventPublisher.publishEvent(event);
        try {
            return event.getReplyFuture().get(3, java.util.concurrent.TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new IllegalStateException("리스너 응답을 받지 못했습니다.", e);
        }
    }
}
