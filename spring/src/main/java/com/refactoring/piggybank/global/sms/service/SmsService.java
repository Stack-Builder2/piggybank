package com.refactoring.piggybank.global.sms.service;

public interface SmsService {

    void sendVerifyNum(String phoneNum);
//    boolean checkVerifyNum(String phoneNum, String verifyNum);
}
