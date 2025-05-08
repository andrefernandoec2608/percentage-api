package com.andre.percentage_api.exceptionHandler;

public class NoPercentageAvailableException extends RuntimeException{
    public NoPercentageAvailableException() {
        super("Fallo simulado y caché vacía o expirada.");
    }
}
