package com.christiandstavares.vendas.exception;

public class IntegridadeDadoVioladaException extends RuntimeException {

    public IntegridadeDadoVioladaException(String message) {
        super(message);
    }

    public IntegridadeDadoVioladaException(String message, Throwable cause) {
        super(message, cause);
    }
}
