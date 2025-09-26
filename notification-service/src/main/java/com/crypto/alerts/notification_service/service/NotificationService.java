package com.crypto.alerts.notification_service.service;

import com.crypto.alerts.notification_service.dto.NotificationRequestDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(NotificationRequestDTO request) {
        System.out.println("Sending email to " + request.getEmail() +
                " | Subject: " + request.getSubject() +
                " | Body: " + request.getBody());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());
        mailSender.send(message);
    }
}
