package com.learning.cursodio.controllers;

import com.learning.cursodio.exceptions.ProductNullException;
import com.learning.cursodio.exceptions.ProductPriceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ProdutoControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> erro() {

        Map<String, Object> body = new HashMap<>();

        body.put("message", "Ocorreu um erro genérico");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ProductNullException.class)
    public ResponseEntity<Object> capturaErroNull() {

        Map<String, Object> body = new HashMap<>();

        body.put("message", "Verifique os campos do produto");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ProductPriceException.class)
    public ResponseEntity<Object> capturaErroPreco() {

        Map<String, Object> body = new HashMap<>();

        body.put("message", "Verifique o preco do produto");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
