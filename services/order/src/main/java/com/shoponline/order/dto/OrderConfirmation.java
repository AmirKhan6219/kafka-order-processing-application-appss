package com.shoponline.order.dto;

import com.shoponline.order.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderConfirmation {
    String orderReference;
    BigDecimal totalAmount;
    PaymentMethod paymentMethod;
    CustomerDto customer;
    List<ProductPurchaseResponse> products;
}
