package com.example.piggybank.global.email.service;

import static com.example.piggybank.global.error.ErrorCode.EMAIL_SEND_FAILED;

import com.example.piggybank.global.error.ErrorCode;
import com.example.piggybank.global.error.exception.BusinessException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
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
            throw new BusinessException("이메일 발송을 실패하였습니다.", EMAIL_SEND_FAILED);
        }
    }

    @Override
    public void sendVerificationEmail(String to, String verificationUrl) {
        Context context = new Context();
        context.setVariable("verificationUrl", verificationUrl);

        String htmlContent = templateEngine.process("email-verification", context);

        sendHtmlMessage(to, "이메일 인증 요청", htmlContent);
    }

}
