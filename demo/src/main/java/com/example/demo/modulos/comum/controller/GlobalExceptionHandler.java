package com.example.demo.modulos.comum.controller;


import com.example.demo.modulos.comum.exception.ErrorMessage;
import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public List<ErrorMessage> handleBeanValidationException(Exception ex) {
        var result = ex instanceof MethodArgumentNotValidException res
                ? res.getBindingResult()
                : ((BindException) ex).getBindingResult();

        return result.getFieldErrors()
                .stream()
                .map(err ->
                        new ErrorMessage(
                                String.format("O campo %s %s", err.getField(), err.getDefaultMessage()),
                                err.getField()))
                .toList();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(NotFoundException ex) {
        return ErrorMessage.of(ex.getMessage());
    }

    @ExceptionHandler(ValidacaoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleValidacaoException(ValidacaoException ex) {
        return ErrorMessage.of(ex.getMessage());
    }
}
