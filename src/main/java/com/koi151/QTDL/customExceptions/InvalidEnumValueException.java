package com.koi151.QTDL.customExceptions;

public class InvalidEnumValueException extends RuntimeException{
    public InvalidEnumValueException(String message) {
        super(message);
    }
}
