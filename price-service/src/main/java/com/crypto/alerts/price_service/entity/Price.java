package com.crypto.alerts.price_service.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {
    private String id;
    private double price;

    public Price(String id, double price) {
        this.id = id;
        this.price = price;
    }
}
