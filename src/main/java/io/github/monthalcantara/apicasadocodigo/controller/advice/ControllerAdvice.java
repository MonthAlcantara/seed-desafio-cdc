package io.github.monthalcantara.apicasadocodigo.controller.advice;

import io.github.monthalcantara.apicasadocodigo.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//2
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrosApi recursoNaoEncontradoException(RecursoNaoEncontradoException e) {
        return new ErrosApi(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrosApi methodNotValidException(MethodArgumentNotValidException e) {
        List<String> listErrors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ErrosApi(listErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrosApi constraintViolationException(ConstraintViolationException e) {
        List<String> listErrors = new ArrayList<>();
        e.getConstraintViolations().stream().forEach(erro -> listErrors.add(erro.getMessageTemplate()));
        return new ErrosApi(listErrors);
    }
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrosApi illegalStateException(IllegalStateException e) {
        return new ErrosApi(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrosApi illegalArgumentException(IllegalArgumentException e) {
        return new ErrosApi(e.getMessage());
    }
}
