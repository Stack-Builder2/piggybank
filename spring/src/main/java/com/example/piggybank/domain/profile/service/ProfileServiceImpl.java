package com.example.piggybank.domain.profile.service;

import com.example.piggybank.domain.auth.entity.User;
import com.example.piggybank.domain.auth.repository.UserRepository;
import com.example.piggybank.domain.profile.dto.resp.ProfileResponse;
import com.example.piggybank.domain.profile.entity.Profile;
import com.example.piggybank.domain.profile.dto.req.ProfileRequest;
import com.example.piggybank.domain.profile.dto.req.ProfileAddGoalRequest;
import com.example.piggybank.domain.profile.dto.req.ProfileAddLimitRequest;

import com.example.piggybank.domain.profile.repository.DomainProfileRepository;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    
    private final DomainProfileRepository profileRepository;
    private final UserRepository userRepository;
    
    @Override
    public Profile updatePassword(ProfileRequest request, String password) {
        
        User user = userRepository.findById(request.userId())
            .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));
        
        Profile profile = Profile.builder()
            .userId(request.userId())
            .build();
        
        return null;
    }
    
    @Override
    public void addLimit(String userId, ProfileAddLimitRequest request) {
        Profile profile = profileRepository.findByUserId(UUID.fromString(userId))
                .orElseThrow(() -> new EntityNotFoundException("프로필이 존재하지 않습니다."));
        profile.setLimit(request.limit());
        profileRepository.save(profile);
    }
    
    @Override
    public void addGoal(String userId, ProfileAddGoalRequest request) {
        Profile profile = profileRepository.findByUserId(UUID.fromString(userId))
            .orElseThrow(() -> new EntityNotFoundException("프로필이 존재하지 않습니다."));
        profile.setGoal(request.goal());
        
        profileRepository.save(profile);
    }

    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        User user = userRepository.findById(request.userId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        Profile profile = profileRepository.findById(user.getUserId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        profileRepository.save(profile);

        return new ProfileResponse(
            user.getUserId(),
            profile.getGoal(),
            profile.getLimit()
        );
    }
}
