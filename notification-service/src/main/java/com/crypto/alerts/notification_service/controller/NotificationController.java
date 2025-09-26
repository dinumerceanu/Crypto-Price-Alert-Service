package com.crypto.alerts.notification_service.controller;

import com.crypto.alerts.notification_service.dto.NotificationRequestDTO;
import com.crypto.alerts.notification_service.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendNotification(@RequestBody NotificationRequestDTO request) {
        notificationService.sendEmail(request);
        return ResponseEntity.ok().build();
    }
}
