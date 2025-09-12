package com.refactoring.piggybank.global.email.controller;

import com.refactoring.piggybank.global.email.dto.EmailByTokenRequest;
import com.refactoring.piggybank.global.email.dto.EmailRequest;
import com.refactoring.piggybank.global.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "이메일 발송", description = "JavaMailSender")
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailRequest emailRequest) {
        emailService.sendVerificationEmail(
            emailRequest.to(),
            emailRequest.subject(),
            emailRequest.verificationUrl()
        );

        return ResponseEntity.ok("이메일이 성공적으로 발송되었습니다.");
    }

    @Operation(summary = "이메일 발송(token url)", description = "JavaMailSender")
    @PostMapping("/send/token")
    public ResponseEntity<String> sendEmailByToken(@Valid @RequestBody EmailByTokenRequest emailRequest) {
        emailService.passwordChangedEmail(
            emailRequest.to(),
            emailRequest.subject()
        );

        return ResponseEntity.ok("이메일이 성공적으로 발송되었습니다.");
    }
}