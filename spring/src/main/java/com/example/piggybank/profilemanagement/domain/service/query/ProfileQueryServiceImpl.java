package com.example.piggybank.profilemanagement.domain.service.query;

import com.example.piggybank.global.error.exception.EntityNotFoundException;
import com.example.piggybank.profilemanagement.domain.entity.Profile;
import com.example.piggybank.profilemanagement.infrastructure.repository.ProfileRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;
    
    @Override
    public Profile findByUserId(UUID userId) {
        Profile profile = null;
        profile = profileRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("프로필이 존재하지 않습니다."));
        return profile;
    }
    
    @Override
    public boolean existsByUserId(UUID userId) {
        return profileRepository.existsById(userId);
    }
}
