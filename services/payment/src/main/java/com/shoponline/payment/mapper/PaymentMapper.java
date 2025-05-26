package com.shoponline.payment.mapper;

import com.shoponline.payment.dto.PaymentDto;
import com.shoponline.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentDto request) {

        if (request == null) {
            return null;
        }

        return Payment.builder()
                .id(request.getId())
                .orderId(request.getOrderId())
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .build();
    }
}
