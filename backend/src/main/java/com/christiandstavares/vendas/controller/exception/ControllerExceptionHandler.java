package com.christiandstavares.vendas.controller.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.christiandstavares.vendas.exception.ArquivoException;
import com.christiandstavares.vendas.exception.AutorizacaoException;
import com.christiandstavares.vendas.exception.IntegridadeDadoVioladaException;
import com.christiandstavares.vendas.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        StandardError error = new StandardError(HttpStatus.NOT_FOUND.toString(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IntegridadeDadoVioladaException.class)
    public ResponseEntity<StandardError> integridadeDadoViolada(IntegridadeDadoVioladaException e, HttpServletRequest request) {

        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validacao(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.toString(), "Erro de validação", System.currentTimeMillis());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.addErro(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AutorizacaoException.class)
    public ResponseEntity<StandardError> autorizacao(AutorizacaoException e, HttpServletRequest request) {

        StandardError error = new StandardError(HttpStatus.FORBIDDEN.toString(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(ArquivoException.class)
    public ResponseEntity<StandardError> arquivo(ArquivoException e, HttpServletRequest request) {

        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.valueOf(e.getErrorCode());
        StandardError error = new StandardError(status.toString(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {

        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {

        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
