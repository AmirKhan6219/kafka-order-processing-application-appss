package com.shoponline.payment.dto;

import com.shoponline.payment.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentNotificationRequest {
    String orderReference;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    String customerFirstname;
    String customerLastname;
    String customerEmail;
}
