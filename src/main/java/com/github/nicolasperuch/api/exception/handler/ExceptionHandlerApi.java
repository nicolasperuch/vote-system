package com.github.nicolasperuch.api.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public abstract class ExceptionHandlerApi {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public String handleValidationExceptions(Exception ex) {
        return "Durante a verificação de seu cpf foi concluido que o mesmo não existe ou é inválido";
    }
}