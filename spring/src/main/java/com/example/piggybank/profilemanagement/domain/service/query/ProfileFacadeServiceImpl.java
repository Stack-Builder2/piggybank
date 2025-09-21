package com.example.piggybank.profilemanagement.domain.service.query;

import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddGoalRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddLimitRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileRequest;
import com.example.piggybank.profilemanagement.api.dto.resp.ProfileResponse;
import com.example.piggybank.profilemanagement.domain.entity.Profile;
import com.example.piggybank.profilemanagement.domain.service.command.ProfileCommandService;
import com.example.piggybank.profilemanagement.domain.service.facade.ProfileQueryService;
import com.example.piggybank.profilemanagement.infrastructure.repository.ProfileRepository;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileFacadeServiceImpl implements ProfileFacadeService {
    
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;
    
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
}
