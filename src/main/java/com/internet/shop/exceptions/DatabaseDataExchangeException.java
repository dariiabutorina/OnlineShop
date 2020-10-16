package com.internet.shop.exceptions;

public class DatabaseDataExchangeException extends RuntimeException {
    public DatabaseDataExchangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
