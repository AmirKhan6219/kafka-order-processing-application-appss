package com.shoponline.notification.kafka;

import com.shoponline.notification.dto.OrderConfirmation;
import com.shoponline.notification.dto.PaymentConfirmation;
import com.shoponline.notification.entity.Notification;
import com.shoponline.notification.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.shoponline.notification.entity.NotificationType.ORDER_CONFIRMATION;
import static com.shoponline.notification.entity.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;

    public NotificationConsumer(NotificationRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "payment-topic", groupId = "paymentGroup")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
    }

    @KafkaListener(topics = "order-topic", groupId = "orderGroup")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
    }
}
