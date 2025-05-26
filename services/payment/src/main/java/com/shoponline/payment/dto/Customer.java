package com.shoponline.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@Data
@Validated
public class Customer {
    Long id;
    @NotNull(message = "Firstname is required")
    String firstname;
    @NotNull(message = "Lastname is required")
    String lastname;
    @NotNull(message = "Email is required")
    @Email(message = "The customer email is not correctly formatted")
    String email;
}
