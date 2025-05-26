package com.shoponline.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shoponline.order.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    Long id;
    String reference;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    Long customerId;
}
