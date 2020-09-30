package com.internet.shop.exceptions;

public class DatabaseDataExchangeFailedException extends RuntimeException {
    public DatabaseDataExchangeFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
