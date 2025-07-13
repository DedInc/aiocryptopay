package com.github.dedinc.aiocryptopay.exceptions;

public class CryptoPayException extends RuntimeException {

    public CryptoPayException(String message) {
        super(message);
    }

    public CryptoPayException(String message, Throwable cause) {
        super(message, cause);
    }
}
