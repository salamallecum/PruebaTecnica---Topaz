package com.pruebaTecnicaTopaz.exception;

//Clase que define la excepción de transaccion incorrecta
public class TransaccionEnEstadoIncorrectoException extends RuntimeException{

    public TransaccionEnEstadoIncorrectoException(String message){
        super(message);
    }

}
