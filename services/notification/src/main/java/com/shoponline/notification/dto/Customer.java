package com.shoponline.notification.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
public class Customer {
    Long customerId;
    String firstname;
    String lastname;
    String email;
}
