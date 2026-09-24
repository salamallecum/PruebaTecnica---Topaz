package com.pruebaTecnicaTopaz.exception;

//Clase que define la excepción de estudiante ya registrado
public class TransaccionEnEstadoIncorrectoException extends RuntimeException{

    public TransaccionEnEstadoIncorrectoException(String message){
        super(message);
    }

}
