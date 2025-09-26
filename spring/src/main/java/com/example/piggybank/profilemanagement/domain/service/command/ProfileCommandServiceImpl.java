package com.example.piggybank.profilemanagement.domain.service.command;

import com.example.piggybank.global.error.exception.EntityNotFoundException;
import com.example.piggybank.profilemanagement.domain.entity.Profile;
import com.example.piggybank.profilemanagement.infrastructure.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    
    @Override
    public void createProfile(UUID userId) {
        Profile profile = Profile.builder()
                .userId(userId)
                    .build();
        profileRepository.save(profile);
    }
    
    @Override
    @Transactional
    public Profile updateLimit(UUID userId, BigDecimal limit) {
        Profile profile = profileRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("프로필이 존재하지 않습니다."));
        profile.updateLimit(limit);
        return profile;
    }
    
    @Override
    @Transactional
    public Profile updateGoal(UUID userId, BigDecimal goal) {
        Profile profile = profileRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("프로필이 존재하지 않습니다."));
        profile.updateGoal(goal);
        return profile;
    }
}
