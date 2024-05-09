package com.newapi.newApi.others;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.newapi.newApi.exceptions.ProductNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<Object> productNotFoundHandler(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exception: Product not found!");
    }
}
