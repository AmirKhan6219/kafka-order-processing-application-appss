package com.shoponline.order.controller;

import com.shoponline.order.dto.OrderDto;
import com.shoponline.order.dto.OrderResponse;
import com.shoponline.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrders(
            @RequestBody @Valid OrderDto orderDto
    ) {
        return new ResponseEntity<>(service.createOrder(orderDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findOrderById(
            @PathVariable("orderId") long orderId
    ){
        return ResponseEntity.ok(service.findOrderById(orderId));
    }
}
