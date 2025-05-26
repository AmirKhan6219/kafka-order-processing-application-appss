package com.shoponline.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {
    private long id;
    @NotNull(message = "Product name is required")
    private String name;
    @NotNull(message = "Product description is required")
    private String description;
    @Positive(message = "Available quantity should be positive")
    private double availableQuantity;
    @Positive(message = "Price should be positive")
    private BigDecimal price;
    @NotNull(message = "Product category is required")
    private long categoryId;
    @NotNull(message = "Product category name is required")
    private String categoryName;
    @NotNull(message = "Product category Description is required")
    private String categoryDescription;
}
