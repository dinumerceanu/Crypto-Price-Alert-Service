package com.crypto.alerts.notification_service.dto;

import lombok.Data;

@Data
public class NotificationRequestDTO {
    private String email;
    private String subject;
    private String body;
}
