package com.gustavoboliveira.cloud_native.temafinal01.exceptions;

public class OperationNotAvailableException extends RuntimeException{

    public OperationNotAvailableException() {
        super("Operation not available for this application");
    }
}
