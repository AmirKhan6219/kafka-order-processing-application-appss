package com.shoponline.notification.dto;

import com.shoponline.notification.entity.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {
    @Column(name = "order_reference")
    String orderReference;
    BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    @Embedded
    Customer customer;
    @ElementCollection
    @CollectionTable(name = "product_notification", joinColumns = @JoinColumn(name = "notification_id"))
    List<Product> products;
}
