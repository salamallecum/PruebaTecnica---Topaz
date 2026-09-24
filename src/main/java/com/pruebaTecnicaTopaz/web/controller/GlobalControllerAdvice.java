package com.pruebaTecnicaTopaz.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//Clase que define el manejo global de excepciones y respuestas en la aplicación
@RestControllerAdvice
public class GlobalControllerAdvice {

    //Método encargado de definir la excepción que se lanza cuando
    /*@ResponseStatus(HttpStatus.NOT_FOUND)
    //@ExceptionHandler(.class)
    public ResponseEntity<Map<String, Object>> handleCardExistsExceptions(CardExistsException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }*/
}