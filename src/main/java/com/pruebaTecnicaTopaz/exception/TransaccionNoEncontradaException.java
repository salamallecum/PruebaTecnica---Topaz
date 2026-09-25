package com.pruebaTecnicaTopaz.exception;

//Clase que define la excepción personalizada para cuando una transacción no es encontrada
public class TransaccionNoEncontradaException extends RuntimeException{

    public TransaccionNoEncontradaException(String message){
        super(message);
    }
}
