package com.pruebaTecnicaTopaz.exception;

//Clase que define la excepción de transaccion vencida
public class TransaccionVencidaException extends RuntimeException{

    public TransaccionVencidaException(String message){
        super(message);
    }
}
