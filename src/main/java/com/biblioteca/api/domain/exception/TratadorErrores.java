package com.biblioteca.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorErrores {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> tratarErrorRecursoNoEncontrado(ResourceNotFoundException e){
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
