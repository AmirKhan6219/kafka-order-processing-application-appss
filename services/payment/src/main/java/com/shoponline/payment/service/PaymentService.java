package com.shoponline.payment.service;

import com.shoponline.payment.dto.PaymentDto;
import com.shoponline.payment.dto.PaymentNotificationRequest;
import com.shoponline.payment.mapper.PaymentMapper;
import com.shoponline.payment.kafka.NotificationProducer;
import com.shoponline.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public PaymentService(PaymentRepository repository, PaymentMapper mapper, NotificationProducer notificationProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.notificationProducer = notificationProducer;
    }

    public Long createPayment(PaymentDto request) {
        var payment = this.repository.save(this.mapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.getOrderReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        request.getCustomer().getFirstname(),
                        request.getCustomer().getLastname(),
                        request.getCustomer().getEmail()
                )
        );
        return null;
    }
}
