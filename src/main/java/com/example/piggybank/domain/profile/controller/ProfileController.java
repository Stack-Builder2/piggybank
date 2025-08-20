package com.example.piggybank.domain.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggybank.domain.profile.dto.req.ProfileAddGoalRequest;
import com.example.piggybank.domain.profile.dto.req.ProfileAddLimitRequest;
import com.example.piggybank.domain.profile.service.ProfileService;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    
    private final ProfileService profileService;
    
    @Operation(summary = "한도 추가", description = "사용자 프로필 사용 한도 추가")
    @PostMapping("/add/limit")
    public ResponseEntity<String> addLimit(ProfileAddLimitRequest request) {
        profileService.addLimit(request);
        return ResponseEntity.ok().body("한도 설정이 완료되었습니다.");
    }
    
    @Operation(summary = "목표 금액 추가", description = "사용자 프로필 목표 금액 추가")
    @PostMapping("/add/goal")
    public ResponseEntity<String> addGoal(ProfileAddGoalRequest request) {
        profileService.addGoal(request);
        return ResponseEntity.ok().body("목표금액 설정이 완료되었습니다.");
    }
}
