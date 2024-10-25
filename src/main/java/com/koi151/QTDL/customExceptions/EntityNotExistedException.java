package com.koi151.QTDL.customExceptions;

import java.io.Serial;

public class EntityNotExistedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5875429336163883270L;

    public EntityNotExistedException(String message) {
        super(message);
    }
}
