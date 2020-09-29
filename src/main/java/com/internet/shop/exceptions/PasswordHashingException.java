package com.internet.shop.exceptions;

public class PasswordHashingException extends RuntimeException {
    public PasswordHashingException(String message, Throwable cause) {
        super(message, cause);
    }
}
