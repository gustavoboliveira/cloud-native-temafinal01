package com.gustavoboliveira.cloud_native.temafinal01.exceptions;

public class NoHistoryFoundException extends RuntimeException{

    public NoHistoryFoundException() {
        super("No history recorded yet");
    }
}
