package com.shoponline.notification.dto;

import com.shoponline.notification.entity.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConfirmation {
    @Transient
    String orderReference;
    BigDecimal amount;
    @Transient
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    String customerFirstname;
    String customerLastname;
    String customerEmail;
}
