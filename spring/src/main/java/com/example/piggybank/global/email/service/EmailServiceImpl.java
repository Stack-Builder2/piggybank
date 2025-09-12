package com.refactoring.piggybank.global.email.service;

import com.refactoring.piggybank.membermanagement.domain.service.TokenService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final TokenService tokenService;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("qotn0327@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("메일 발송 실패", e);
        }
    }

    @Override
    public void sendHtmlMessage(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("qotn0327@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);  // true는 HTML 형식을 지원하도록 설정

            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("HTML 이메일 발송 중 오류 발생", e);
        }
    }

    public void sendVerificationEmail(String to, String name, String verificationUrl) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("verificationUrl", verificationUrl);

        String htmlContent = templateEngine.process("email-verification", context);

        sendHtmlMessage(to, "이메일 인증 요청", htmlContent);
    }

    public void passwordChangedEmail(String to, String name) {
        String token = tokenService.saveTempToken(to, Duration.ofMinutes(10));
        String verificationUrl = "http://localhost:8080/password/verify?token=" + token;

        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("verificationUrl", verificationUrl);

        String htmlContent = templateEngine.process("email-verification", context);

        sendHtmlMessage(to, "이메일 인증 요청", htmlContent);
    }
}
