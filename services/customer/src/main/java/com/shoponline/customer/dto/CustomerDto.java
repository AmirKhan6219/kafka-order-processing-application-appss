package com.shoponline.customer.dto;

import com.shoponline.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    @Email(message = "Customer Email is not a valid email address")
    private String email;
    private Address address;
}

