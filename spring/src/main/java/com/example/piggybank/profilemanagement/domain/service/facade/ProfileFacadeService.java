package com.example.piggybank.profilemanagement.domain.service.facade;


import com.example.piggybank.global.common.dto.ResponseDto;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddGoalRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddLimitRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileRequest;
import com.example.piggybank.profilemanagement.api.dto.resp.ProfileResponse;
import com.example.piggybank.profilemanagement.domain.entity.Profile;
import org.springframework.http.ResponseEntity;

public interface ProfileFacadeService {
    
    ResponseEntity<ResponseDto> updateLimit(String userId, ProfileAddLimitRequest request);
    
    ResponseEntity<ResponseDto> updateGoal(String userId, ProfileAddGoalRequest request);

    ResponseEntity<ProfileResponse> createProfile(ProfileRequest request);
    
    ResponseEntity<ResponseDto> compareLimit(String userId);
    ResponseEntity<ResponseDto> compareGoal(String userId);
}
