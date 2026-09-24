package com.pruebaTecnicaTopaz.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

//Clase que define los códigos de error que se pueden presentar en la aplicación
@Getter
@AllArgsConstructor
public enum ErrorCatalog {

    //Códigos de error definidos para la aplicación, con su respectivo mensaje de error
    PRUEBA("ERR-EST-001", "PRUEBA.");

    private final String code;
    private final String message;
}
