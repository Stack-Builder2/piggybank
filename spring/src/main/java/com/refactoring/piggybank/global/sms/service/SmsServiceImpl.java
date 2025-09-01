package com.refactoring.piggybank.global.sms.service;

import com.refactoring.piggybank.global.sms.util.SmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsUtil smsUtil;

    @Override
    public void sendVerifyNum(String phoneNum) {
        Random random = new Random();
        // 6자리 랜덤 값 입력
        int verificationCode = random.nextInt(900000) + 100000;
        String verificationCodeStr = String.valueOf(verificationCode);

        // 인증번호 발신
        smsUtil.sendOne(phoneNum, verificationCodeStr);
    }
}
