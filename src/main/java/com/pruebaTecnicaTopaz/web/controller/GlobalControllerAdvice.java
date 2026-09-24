package com.pruebaTecnicaTopaz.web.controller;


import com.pruebaTecnicaTopaz.exception.PruebaException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.pruebaTecnicaTopaz.domain.dto.response.ErrorResponse;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.pruebaTecnicaTopaz.util.ErrorCatalog.PRUEBA;

//Clase que define el manejo global de excepciones y respuestas en la aplicación
@RestControllerAdvice
public class GlobalControllerAdvice {

    //Método encargado de definir la excepción que se lanza cuando
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PruebaException.class)
    public ErrorResponse handEstudianteNotFoundException() {
        return ErrorResponse.builder()
                .codigoError(PRUEBA.getCode())
                .mensaje(PRUEBA.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Método encargado de definir la excepción que se lanza cuando hay un error en la validación de los datos del estudiante
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        //Obtenemos el BindingResult que contiene los errores de validación
        BindingResult bindingResult = ex.getBindingResult();

        return ErrorResponse.builder()
                .codigoError(PRUEBA.getCode())
                .mensaje(PRUEBA.getMessage())
                .detalles(bindingResult.getFieldErrors()
                        .stream()
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }
}