package com.gustavoboliveira.cloud_native.temafinal01.exceptions;

public class DivisionByZeroException extends RuntimeException{

    public DivisionByZeroException() {
        super("Can't divide by zero");
    }
}
