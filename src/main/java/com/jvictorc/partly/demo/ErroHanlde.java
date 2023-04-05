package com.jvictorc.partly.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ErroHanlde {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<List<ErrorView>> handleWithBadRequestValidator(
            MethodArgumentNotValidException e
    ) {
        var erros = e.getFieldErrors();

        var errosMapeados = erros.stream().map(ErrorView::new).toList();

        return ResponseEntity.badRequest().body(errosMapeados);
    }
}
