package com.shoponline.notification.entity;

import com.shoponline.notification.dto.OrderConfirmation;
import com.shoponline.notification.dto.PaymentConfirmation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private LocalDateTime notificationDate;
    @Embedded
    private OrderConfirmation orderConfirmation;
    @Embedded
    private PaymentConfirmation paymentConfirmation;
}
