package com.shoponline.order.service;

import com.shoponline.order.dto.OrderLineRequestDto;
import com.shoponline.order.dto.OrderLineResponse;
import com.shoponline.order.mapper.OrderLineMapper;
import com.shoponline.order.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {
    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public OrderLineService(OrderLineRepository repository, OrderLineMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public Long saveOrderLine(OrderLineRequestDto orderLineRequestDto) {
        var order = mapper.toOrderLine(orderLineRequestDto);
        return repository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(long orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
