package com.example.piggybank.membermanagement.domain.service;


public interface PasswordChangeService {

    void requestReset(String email);
    void resetPassword(String token, String newPassword);
}
