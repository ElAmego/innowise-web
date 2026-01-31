package com.egoramel.webproject.exception;

@SuppressWarnings("unused")
public class CustomException extends Exception {
    public CustomException() {
        super();
    }
    public CustomException(final String message) {
        super(message);
    }
    public CustomException(final Throwable throwable) {
        super(throwable);
    }
    public CustomException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}