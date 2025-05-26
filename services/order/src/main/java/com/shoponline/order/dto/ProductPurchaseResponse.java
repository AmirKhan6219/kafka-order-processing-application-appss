package com.shoponline.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductPurchaseResponse {
    private long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
