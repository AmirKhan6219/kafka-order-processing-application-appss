package com.shoponline.order.exception;

import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BusinessException extends RuntimeException {
    private final String message;
    public BusinessException(String message) {
        this.message = message;
    }
}
