package com.refactoring.piggybank.global.email.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendHtmlMessage(String to, String subject, String htmlContent);
    void sendVerificationEmail(String to, String name, String verificationUrl);
}
