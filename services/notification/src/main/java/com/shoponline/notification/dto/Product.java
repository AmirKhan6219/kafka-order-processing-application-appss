package com.shoponline.notification.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    Long productId;
    String name;
    String description;
    BigDecimal price;
    double quantity;
}
