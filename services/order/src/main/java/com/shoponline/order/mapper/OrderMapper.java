package com.shoponline.order.mapper;

import com.shoponline.order.dto.OrderDto;
import com.shoponline.order.dto.OrderResponse;
import com.shoponline.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .customerId(orderDto.getCustomerId())
                .reference(orderDto.getReference())
                .totalAmount(orderDto.getAmount())
                .paymentMethod(orderDto.getPaymentMethod())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
