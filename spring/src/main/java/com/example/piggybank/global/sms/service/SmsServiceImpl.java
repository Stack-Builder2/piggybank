package com.example.piggybank.global.sms.service;

import com.example.piggybank.global.sms.util.SmsUtil;
import java.time.Duration;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsUtil smsUtil;
    private final StringRedisTemplate redisTemplate;
    private static final String PREFIX = "sms:"; // Redis 키 접두사
    private static final int VERIFICATION_CODE_LENGTH = 6; // 인증번호 길이
    private static final Duration TTL = Duration.ofMinutes(5); // 인증번호 유효 시간: 5분

    @Override
    public void sendVerifyNum(String phoneNum) {
        String verificationCode = createVerificationCode();

        smsUtil.sendOne(phoneNum, verificationCode);

        redisTemplate.opsForValue().set(PREFIX + phoneNum, verificationCode, TTL);
    }

    @Override
    public boolean checkVerifyNum(String phoneNum, String verifyNum) {
        String storedCode = redisTemplate.opsForValue().get(PREFIX + phoneNum);

        if (storedCode == null || !storedCode.equals(verifyNum)) {
            return false;
        }

        redisTemplate.delete(PREFIX + phoneNum);
        return true;
    }

    private String createVerificationCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
            codeBuilder.append(random.nextInt(10)); // 0-9 사이의 숫자
        }
        return codeBuilder.toString();
    }
}