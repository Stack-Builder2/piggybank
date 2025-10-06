package com.example.piggybank.global.sms.controller;

import com.example.piggybank.global.sms.dto.SmsRequest;
import com.example.piggybank.global.sms.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @Operation(summary = "sms 발송", description = "coolSMS")
    @PostMapping("/send")
    public ResponseEntity<String> sendVerifyNum(@Valid @RequestBody SmsRequest request){
        try {
            if(request.to().contains("-")){
                return new ResponseEntity<>("하이폰 제거 후 번호 다시 입력", HttpStatus.OK);
            }
            smsService.sendVerifyNum(request.to());
            return new ResponseEntity<>("인증번호 발송 완료", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<Object> checkVerifyNum(@RequestParam("phoneNum") String phoneNum,
        @RequestParam("verifyNum") String verifyNum){
        try {
            boolean check = smsService.checkVerifyNum(phoneNum, verifyNum);
            return new ResponseEntity<>(check, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}