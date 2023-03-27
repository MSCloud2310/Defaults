package com.defaults.marketplace.msusers.exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message){
        super(message);
    }
}
