package com.shoponline.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PurchaseRequestDto {
    @NotNull(message = "Product is mandatory")
    Long productId;
    @Positive(message = "Quantity is mandatory")
    double quantity;
}

