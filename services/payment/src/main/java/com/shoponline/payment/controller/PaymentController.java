package com.shoponline.payment.controller;

import com.shoponline.payment.dto.PaymentDto;
import com.shoponline.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Long> createPayment(
            @RequestBody @Valid PaymentDto request
    ) {
        return ResponseEntity.ok(this.service.createPayment(request));
    }
}
