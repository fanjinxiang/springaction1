package com.springaction.springaction1.exception;

public class RollbackException extends Exception {
    public RollbackException() {
        super();
    }

    public RollbackException(String message){
        super(message);
    }
}
