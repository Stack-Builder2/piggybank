package com.example.piggybank.profilemanagement.domain.service.facade;

import com.example.piggybank.global.common.dto.ResponseDto;
import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.BusinessException;
import com.example.piggybank.global.error.exception.EntityNotFoundException;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddGoalRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileAddLimitRequest;
import com.example.piggybank.profilemanagement.api.dto.req.ProfileRequest;
import com.example.piggybank.profilemanagement.api.dto.resp.ProfileResponse;
import com.example.piggybank.profilemanagement.domain.entity.Profile;
import com.example.piggybank.profilemanagement.domain.service.command.ProfileCommandService;
import com.example.piggybank.profilemanagement.domain.service.query.ProfileQueryService;
import com.example.piggybank.profilemanagement.event.GoalCompareRequestEvent;
import com.example.piggybank.profilemanagement.event.LimitCompareRequestEvent;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileFacadeServiceImpl implements ProfileFacadeService {
    
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;
    private final ApplicationEventPublisher eventPublisher;
    
    private UUID toUUID(String userId){
        try{
            return UUID.fromString(userId);
        } catch (Exception e){
            throw new BusinessException("잘못된 형식의 사용자 ID입니다.", ErrorCode.INVALID_TYPE_VALUE);
        }
    }
    
    @Override
    public ResponseEntity<ResponseDto> updateLimit(String userId, @Valid ProfileAddLimitRequest request) {
        if (!profileQueryService.existsByUserId(toUUID(userId))) {
            throw new EntityNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Profile profile = profileCommandService.updateLimit(toUUID(userId), request.limit());
        return ResponseDto.success("한도 금액을 업데이트했습니다.");
    }
    
    @Override
    public ResponseEntity<ResponseDto> updateGoal(String userId, @Valid ProfileAddGoalRequest request) {
        if (!profileQueryService.existsByUserId(toUUID(userId))) {
            throw new EntityNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Profile profile = profileCommandService.updateGoal(toUUID(userId), request.goal());
        return ResponseDto.success("목표 금액을 업데이트했습니다.");
    }

    @Override
    public ResponseEntity<ProfileResponse> createProfile(@Valid ProfileRequest request) {
        if(profileQueryService.existsByUserId(request.userId())){
            throw new BusinessException("이미 존재하는 유저 프로필입니다.", ErrorCode.PROFILE_DUPLICATION);
        }
        profileCommandService.createProfile(request.userId());

        return ProfileResponse.success(request.userId(), "프로필 생성이 완료되었습니다.");
    }
    
    @Override
    public ResponseEntity<ResponseDto> compareLimit(String userId) {
        Profile profile = profileQueryService.findByUserId(UUID.fromString(userId));
        if(profile.getLimit().compareTo(BigDecimal.ZERO) < 1) {
            throw new BusinessException("한도 금액이 올바르지 않습니다", ErrorCode.INVALID_INPUT_VALUE);
        }
        LimitCompareRequestEvent event = new LimitCompareRequestEvent(
            this,
            profile,
            "Compare Limit"
        );
        eventPublisher.publishEvent(event);
        String message = null;
        try {
            message = event.getReplyFuture().get(3, TimeUnit.SECONDS);
            return ResponseDto.success(message);
        } catch (Exception e) {
            throw new BusinessException("리스너 응답을 받지 못했습니다.", ErrorCode.GATEWAY_TIMEOUT);
        }
    }
    
    @Override
    public ResponseEntity<ResponseDto> compareGoal(String userId) {
        Profile profile = profileQueryService.findByUserId(UUID.fromString(userId));
        if(profile.getGoal() == null){
            throw new BusinessException("목표 금액을 설정하지 않았습니다.", ErrorCode.INVALID_INPUT_VALUE);
        }
        if(profile.getGoal().compareTo(BigDecimal.ZERO) < 1) {
            throw new BusinessException("목표 금액이 올바르지 않습니다.", ErrorCode.INVALID_INPUT_VALUE);
        }
        GoalCompareRequestEvent event = new GoalCompareRequestEvent(
            this,
            profile,
            "Compare Goal"
        );
        eventPublisher.publishEvent(event);
        String message = null;
        try {
            message = event.getReplyFuture().get(3, TimeUnit.SECONDS);
            return ResponseDto.success(message);
        } catch (Exception e) {
            throw new BusinessException("리스너 응답을 받지 못했습니다.", ErrorCode.GATEWAY_TIMEOUT);
        }
    }
}
