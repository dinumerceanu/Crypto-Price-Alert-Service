package com.crypto.alerts.price_service.controller;

import com.crypto.alerts.price_service.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices/{symbol}")
    public ResponseEntity<BigDecimal> getPrice(@PathVariable String symbol) {
        return ResponseEntity.ok(priceService.fetchPrice(symbol));
    }
}
