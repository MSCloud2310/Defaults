package com.defaults.marketplace.msorders.exceptions;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException(String message) {
        super(message);
    }
}
