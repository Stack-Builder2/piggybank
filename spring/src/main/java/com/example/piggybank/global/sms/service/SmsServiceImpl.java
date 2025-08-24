package com.example.piggybank.global.sms.service;

import com.example.piggybank.global.sms.dto.SmsRequest;
import com.example.piggybank.global.sms.util.SmsUtil;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
