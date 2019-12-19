package com.christiandstavares.vendas.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<CampoMensagem> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<CampoMensagem> getErrors() {
        return errors;
    }

    public void addError(String campo, String mensagem) {
        this.errors.add(new CampoMensagem(campo, mensagem));
    }
}
