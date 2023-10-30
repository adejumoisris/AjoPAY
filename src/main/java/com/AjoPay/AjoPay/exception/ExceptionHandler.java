package com.AjoPay.AjoPay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handleCustomException(CustomException ex){
        return new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());

    }
}
