package com.uade.tpo.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409 = conflicto de datos existentes
public class UserDuplicateException extends RuntimeException {

    public UserDuplicateException(String message) {
        super(message);
    }
}