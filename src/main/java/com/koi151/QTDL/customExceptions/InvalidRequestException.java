package com.koi151.QTDL.customExceptions;

public class InvalidRequestException extends  RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
