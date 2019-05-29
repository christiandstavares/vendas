package com.christiandstavares.vendas.exception;

public class AutorizacaoException extends RuntimeException {

    public AutorizacaoException(String message) {
        super(message);
    }

    public AutorizacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
