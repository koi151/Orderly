package com.koi151.QTDL.customExceptions;

public class EntityDeletionFailed extends RuntimeException {
    public EntityDeletionFailed(String message) {
        super(message);
    }
}
