package com.internet.shop.exceptions;

public class DataBaseConnectionExchangeFailedException extends RuntimeException {
    public DataBaseConnectionExchangeFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
