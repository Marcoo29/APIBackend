package com.uade.tpo.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La categoría que se intenta agregar está duplicada")
public class CategoryDuplicateException extends RuntimeException {
    public CategoryDuplicateException() {
        super("La categoría que se intenta agregar está duplicada");
    }

    public CategoryDuplicateException(String message) {
        super(message);
    }
}
