package com.example.piggybank.profilemanagement.api.controller;

import com.example.piggybank.global.common.dto.ResponseDto;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddGoalRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddLimitRequest;
import com.example.piggybank.profilemanagement.domain.service.facade.ProfileFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/profile")
@RequiredArgsConstructor
public class ProfileController {
    
    private final ProfileFacadeService profileFacadeService;
    
    @Operation(summary = "한도 추가", description = "사용자 프로필 사용 한도 추가")
    @PostMapping("/add/limit")
    public ResponseEntity<ResponseDto> addLimit(@AuthenticationPrincipal String userId,@RequestBody ProfileAddLimitRequest request) {
        ResponseEntity<ResponseDto> response = profileFacadeService.updateLimit(userId, request);
        return response;
    }
    
    @Operation(summary = "목표 금액 추가", description = "사용자 프로필 목표 금액 추가")
    @PostMapping("/add/goal")
    public ResponseEntity<ResponseDto> addGoal(@AuthenticationPrincipal String userId, @RequestBody ProfileAddGoalRequest request) {
        ResponseEntity<ResponseDto> response =  profileFacadeService.updateGoal(userId, request);
        return response;
    }
    
    @Operation(summary = "limit 비교", description = "한도 금액과 account consumption 비교")
    @GetMapping("/co-limit")
    public ResponseEntity<ResponseDto> coLimit(@AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = profileFacadeService.compareLimit(userId);
        return response;
    }
    
    @Operation(summary = "goal 비교", description = "목표 금액과 account balance 비교")
    @GetMapping("/co-goal")
    public ResponseEntity<ResponseDto> coGoal(@AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = profileFacadeService.compareGoal(userId);
        return response;
    }
}
