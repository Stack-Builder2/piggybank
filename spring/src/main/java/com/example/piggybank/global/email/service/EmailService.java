package com.example.piggybank.global.email.service;

public interface EmailService {
    void sendHtmlMessage(String to, String subject, String htmlContent);
    void sendVerificationEmail(String to, String verificationUrl);
}
