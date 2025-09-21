package com.example.piggybank.profilemanagement.domain.service.query;


import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddGoalRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddLimitRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileRequest;
import com.example.piggybank.profilemanagement.api.dto.resp.ProfileResponse;
import com.example.piggybank.profilemanagement.domain.entity.Profile;

public interface ProfileFacadeService {
    
    Profile updateLimit(String userId, ProfileAddLimitRequest request);
    
    Profile updateGoal(String userId, ProfileAddGoalRequest request);

    ProfileResponse createProfile(ProfileRequest request);
}
