package com.boc.autoparking.exception;

public class OutOfGridBoundaryException extends RuntimeException {
    public OutOfGridBoundaryException() {
        super();
    }
    public OutOfGridBoundaryException(String s) {
        super(s);
    }
    public OutOfGridBoundaryException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public OutOfGridBoundaryException(Throwable throwable) {
        super(throwable);
    }
}
