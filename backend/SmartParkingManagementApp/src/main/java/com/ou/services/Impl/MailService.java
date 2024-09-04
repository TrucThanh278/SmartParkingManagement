package com.ou.services.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendVerificationEmail(String toEmail, String token) {
        String subject = "Xác thực tài khoản của bạn";
        String confirmationUrl = "http://localhost:8080/SmartParkingManagementApp/api/verify?token=" + token;
        String message = "Để xác thực tài khoản của bạn, vui lòng nhấp vào đường link sau: " + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(fromEmail);
        email.setTo(toEmail);
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }
}
