package com.pruebaTecnicaTopaz.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.pruebaTecnicaTopaz.domain.dto.response.ErrorResponse;

import java.time.LocalDateTime;

import static com.pruebaTecnicaTopaz.util.ErrorCatalog.PRUEBA;

//Clase que define el manejo global de excepciones y respuestas en la aplicación
@RestControllerAdvice
public class GlobalControllerAdvice {

    //Método encargado de definir la excepción que se lanza cuando
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    //@ExceptionHandler(.class)
    public ErrorResponse handEstudianteNotFoundException() {
        return ErrorResponse.builder()
                .codigoError(PRUEBA.getCode())
                .mensaje(PRUEBA.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}