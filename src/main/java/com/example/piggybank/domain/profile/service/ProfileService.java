package com.example.piggybank.domain.profile.service;

import com.example.piggybank.domain.profile.dto.resp.ProfileResponse;
import com.example.piggybank.domain.profile.entity.Profile;
import com.example.piggybank.domain.profile.dto.req.ProfileRequest;
import com.example.piggybank.domain.profile.dto.req.ProfileAddLimitRequest;
import com.example.piggybank.domain.profile.dto.req.ProfileAddGoalRequest;


public interface ProfileService {
    
    Profile updatePassword(ProfileRequest request, String password);
    
    void addLimit(String userId, ProfileAddLimitRequest request);
    
    void addGoal(String userId, ProfileAddGoalRequest request);

    ProfileResponse createProfile(ProfileRequest request);
}
