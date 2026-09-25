package com.pruebaTecnicaTopaz.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

//Clase que define los códigos de error que se pueden presentar en la aplicación
@Getter
@AllArgsConstructor
public enum ErrorCatalog {

    //Códigos de error definidos para la aplicación, con su respectivo mensaje de error
    ESTADO_INCORRECTO("ERR-EST-001", "Transacción en estado incorrecto para la operación solicitada"),
    VENCIDA("ERR-VEN-001", "Transacción no puede ser reversada, ya que fue creada hace más de 24 horas."),
    NO_ENCONTRADA("ERR-NEN-001", "Transacción no encontrada");

    private final String code;
    private final String message;
}
