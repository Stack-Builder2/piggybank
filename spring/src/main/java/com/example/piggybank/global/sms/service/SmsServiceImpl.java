package com.example.piggybank.global.sms.service;

import com.example.piggybank.global.sms.util.SmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsUtil smsUtil;
    private final StringRedisTemplate redisTemplate;

    @Override
    public void sendVerifyNum(String phoneNum) {

    }

    @Override
    public boolean checkVerifyNum(String phoneNum, String verifyNum) {



        return false;
    }
}