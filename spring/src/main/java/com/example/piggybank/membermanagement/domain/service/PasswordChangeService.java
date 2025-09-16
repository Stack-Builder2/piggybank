package com.example.piggybank.membermanagement.domain.service;


public interface PasswordChangeService {

    void requestResetPassword(String email);
    void resetPassword(String token, String newPassword);
}
