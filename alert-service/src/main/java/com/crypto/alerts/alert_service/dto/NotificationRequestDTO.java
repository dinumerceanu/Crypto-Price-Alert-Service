package com.crypto.alerts.alert_service.dto;

import lombok.Data;

@Data
public class NotificationRequestDTO {
    private String email;
    private String subject;
    private String body;
}
