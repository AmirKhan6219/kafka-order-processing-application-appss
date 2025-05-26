package com.shoponline.order.mapper;

import com.shoponline.order.dto.OrderLineRequestDto;
import com.shoponline.order.dto.OrderLineResponse;
import com.shoponline.order.entity.Order;
import com.shoponline.order.entity.OrderLine;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequestDto orderLineRequestDto) {
        return OrderLine.builder()
                .id(orderLineRequestDto.getId())
                .quantity(orderLineRequestDto.getQuantity())
                .order(
                        Order.builder()
                                .id(orderLineRequestDto.getOrderId())
                                .build()
                )
                .productId(orderLineRequestDto.getProductId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
