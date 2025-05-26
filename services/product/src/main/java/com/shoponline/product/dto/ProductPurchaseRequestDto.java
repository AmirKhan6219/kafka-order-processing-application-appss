package com.shoponline.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseRequestDto {
    @NotNull(message = "Product is mandatory")
    long productId;
    @Positive(message = "Quantity is mandatory")
    double quantity;
}
