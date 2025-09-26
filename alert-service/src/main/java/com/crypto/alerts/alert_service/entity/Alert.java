package com.crypto.alerts.alert_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "alerts")
@Data
public class Alert {

    @Id
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false, precision = 18, scale = 8)
    private BigDecimal threshold;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Direction direction;

    public enum Direction {
        ABOVE, BELOW
    }
}
