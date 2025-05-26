package com.shoponline.order.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorResponse {
    private Date timestamp;
    private String message;
    private String details;
}
