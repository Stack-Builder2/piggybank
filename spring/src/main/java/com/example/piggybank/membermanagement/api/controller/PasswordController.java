package com.example.piggybank.membermanagement.api.controller;

import com.example.piggybank.global.email.service.EmailService;
import com.example.piggybank.membermanagement.api.dto.request.PasswordChangeRequest;
import com.example.piggybank.membermanagement.api.dto.request.ResetRequest;
import com.example.piggybank.membermanagement.domain.service.PasswordChangeService;
import com.example.piggybank.membermanagement.domain.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/password")
public class PasswordController {

    private final TokenService tokenService;
    private final PasswordChangeService passwordChangeService;
    private final EmailService emailService;

    @Operation(summary = "비밀번호 변경 토큰 검증", description = "이메일 링크에 포함된 토큰이 유효한지 확인")
    @GetMapping("/verify")
    public ResponseEntity<?> verifyResetToken(@RequestParam String token) {
        String email = tokenService.get(token);
        if(email == null) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "토큰이 만료되었거나 유효하지 않습니다."));
        }

        return ResponseEntity.ok(Map.of(
            "message", "토큰이 유효합니다.",
            "token", token,
            "email", email
        ));
    }

    @Operation(summary = "비밀번호 변경", description = "토큰이 유효하면 새로운 비밀번호로 변경")
    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestParam PasswordChangeRequest request) {
        String email = tokenService.consume(request.token());
        if(email == null) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "토큰이 만료되었거나 이미 사용되었습니다."));
        }

        passwordChangeService.resetPassword(email, request.password());
        return ResponseEntity.ok(Map.of("message", "비밀번호가 성공적으로 변경되었습니다."));
    }

    @PostMapping("/request-reset")
    public ResponseEntity<?> requestReset(@RequestBody ResetRequest request) {
        emailService.passwordChangedEmail(request.email());
        return ResponseEntity.ok(Map.of("message", "비밀번호 변경 인증 이메일이 발송되었습니다."));
    }

}
