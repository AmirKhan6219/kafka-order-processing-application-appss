package com.shoponline.order.controller;

import com.shoponline.order.dto.OrderLineResponse;
import com.shoponline.order.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

    private final OrderLineService service;

    public OrderLineController(OrderLineService service) {
        this.service = service;
    }

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable("order-id") long orderId
    ) {
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }

}
