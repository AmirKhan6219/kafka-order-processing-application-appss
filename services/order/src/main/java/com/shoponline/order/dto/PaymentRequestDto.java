package com.shoponline.order.dto;


import com.shoponline.order.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentRequestDto {
    BigDecimal amount;
    PaymentMethod paymentMethod;
    Long orderId;
    String orderReference;
    CustomerDto customer;
}
