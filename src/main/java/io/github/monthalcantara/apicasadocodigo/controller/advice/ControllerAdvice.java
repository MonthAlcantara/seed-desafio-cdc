package io.github.monthalcantara.apicasadocodigo.controller.advice;

import io.github.monthalcantara.apicasadocodigo.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrosApi recursoNaoEncontradoException(RecursoNaoEncontradoException e){
        return new ErrosApi(e.getMessage());
    }
}
