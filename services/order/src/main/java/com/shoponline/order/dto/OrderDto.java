package com.shoponline.order.dto;

import com.shoponline.order.entity.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    Long id;
    String reference;
    @Positive(message = "Order amount should be positive")
    BigDecimal amount;
    @NotNull(message = "Payment method should be precised")
    PaymentMethod paymentMethod;
    @NotNull(message = "Customer should be present")
    Long customerId;
    @NotEmpty(message = "You should at least purchase one product")
    List<PurchaseRequestDto> products;
}
