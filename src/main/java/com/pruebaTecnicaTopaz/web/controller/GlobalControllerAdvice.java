package com.pruebaTecnicaTopaz.web.controller;


import com.pruebaTecnicaTopaz.exception.TransaccionEnEstadoIncorrectoException;
import com.pruebaTecnicaTopaz.exception.TransaccionVencidaException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.pruebaTecnicaTopaz.domain.dto.response.ErrorResponse;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.pruebaTecnicaTopaz.util.ErrorCatalog.*;

//Clase que define el manejo global de excepciones y respuestas en la aplicación
@RestControllerAdvice
public class GlobalControllerAdvice {

    //Método encargado de definir la excepción que se lanza cuando la transaccion tiene estado incorrecto
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TransaccionEnEstadoIncorrectoException.class)
    public ErrorResponse handTransaccionEnEstadoIncorrectoException() {
        return ErrorResponse.builder()
                .codigoError(ESTADO_INCORRECTO.getCode())
                .mensaje(ESTADO_INCORRECTO.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Método encargado de definir la excepción que se lanza cuando la transaccion está vencida
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(TransaccionVencidaException.class)
    public ErrorResponse handTransaccionVencidaException() {
        return ErrorResponse.builder()
                .codigoError(VENCIDA.getCode())
                .mensaje(VENCIDA.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}