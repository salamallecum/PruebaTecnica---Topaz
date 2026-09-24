package com.pruebaTecnicaTopaz.exception;

//Clase que define la excepción de estudiante ya registrado
public class PruebaException extends RuntimeException{

    public PruebaException(String message){
        super(message);
    }

}
