package com.jvictorc.partly.demo;

import org.springframework.validation.FieldError;

public record ErrorView(
        String campo,
        String message
) {
    public ErrorView(FieldError e) {
        this(e.getField(), e.getDefaultMessage());
    }
}

