package com.example.piggybank.profilemanagement.api.controller;

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
    public ResponseEntity<String> addLimit(@AuthenticationPrincipal String userId,@RequestBody ProfileAddLimitRequest request) {
        profileFacadeService.updateLimit(userId, request);
        return ResponseEntity.ok().body("한도 설정이 완료되었습니다.");
    }
    
    @Operation(summary = "목표 금액 추가", description = "사용자 프로필 목표 금액 추가")
    @PostMapping("/add/goal")
    public ResponseEntity<String> addGoal(@AuthenticationPrincipal String userId, @RequestBody ProfileAddGoalRequest request) {
        profileFacadeService.updateGoal(userId, request);
        return ResponseEntity.ok().body("목표금액 설정이 완료되었습니다.");
    }
    
    @Operation(summary = "limit 비교", description = "한도 금액과 account consumption 비교")
    @GetMapping("/co-limit")
    public ResponseEntity<String> coLimit(@AuthenticationPrincipal String userId) {
        String response = profileFacadeService.compareLimit(userId);
        return ResponseEntity.ok().body(response);
    }
    
    @Operation(summary = "goal 비교", description = "목표 금액과 account balance 비교")
    @GetMapping("/co-goal")
    public ResponseEntity<String> coGoal(@AuthenticationPrincipal String userId) {
        String response = profileFacadeService.compareGoal(userId);
        return ResponseEntity.ok().body(response);
    }
}
