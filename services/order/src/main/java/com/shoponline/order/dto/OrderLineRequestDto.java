package com.shoponline.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineRequestDto {
    Long id;
    Long orderId;
    Long productId;
    double quantity;
}
