package com.shoponline.payment.dto;

import com.shoponline.payment.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class PaymentDto {
    Long id;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    Long orderId;
    String orderReference;
    Customer customer;
}
