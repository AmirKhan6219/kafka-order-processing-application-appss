package com.shoponline.order.service;

import com.shoponline.order.customer.CustomerClient;
import com.shoponline.order.dto.*;
import com.shoponline.order.exception.BusinessException;
import com.shoponline.order.exception.ResourceNotFoundException;
import com.shoponline.order.kafka.OrderProducer;
import com.shoponline.order.mapper.OrderMapper;
import com.shoponline.order.payment.PaymentClient;
import com.shoponline.order.product.ProductClient;
import com.shoponline.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderLineService orderLineService;
    private final OrderMapper mapper;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository repository, CustomerClient customerClient, ProductClient productClient, PaymentClient paymentClient, OrderLineService orderLineService, OrderMapper mapper, OrderProducer orderProducer) {
        this.repository = repository;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.paymentClient = paymentClient;
        this.orderLineService = orderLineService;
        this.mapper = mapper;
        this.orderProducer = orderProducer;
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        // Check the Customer -> OpenFeign
        var customer = customerClient.findCustomerById(orderDto.getCustomerId())
                .orElseThrow(()-> new BusinessException("Cannot create order:: No customer exists with the provided ID"+ orderDto.getCustomerId()));

        // Purchase the product
        var purchasedProducts = productClient.purchaseProducts(orderDto.getProducts());

        // Persist the order
        var order = repository.save(mapper.toOrder(orderDto));

        // Persist OrderLines
        for(PurchaseRequestDto purchaseRequestDto : orderDto.getProducts()){
            orderLineService.saveOrderLine(
                    new OrderLineRequestDto(
                            null,
                            order.getId(),
                            purchaseRequestDto.getProductId(),
                            purchaseRequestDto.getQuantity()
                    )
            );
        }
        // Start payment process
        var paymentRequest = new PaymentRequestDto(
                orderDto.getAmount(),
                orderDto.getPaymentMethod(),
                orderDto.getId(),
                orderDto.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        // Send the order confirmation to notification service
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderDto.getReference(),
                        orderDto.getAmount(),
                        orderDto.getPaymentMethod(),
                        customer,
                        purchasedProducts
                        )
        );

        return orderDto;
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findOrderById(long orderId) {
        var order = repository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(orderId)
                );

        return mapper.toOrderResponse(order);
    }
}
