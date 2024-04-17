package com.AjoPay.AjoPay.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private String message;
    protected HttpStatus status;

    public CustomException(){}
    public CustomException( String msg){
        super(msg);
        this.message=msg;

    }

    public CustomException(String message, HttpStatus status) {
        this.message = message;
        this.status= status;
    }
}
