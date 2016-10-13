package com.haulmont.testapp.exception;

/**
 * Group containing students delete exception
 */
public class GroupDeleteException extends Exception {
    public GroupDeleteException() {

    }

    public GroupDeleteException(String message) {
        super(message);
    }

    public GroupDeleteException(Throwable cause) {
        super(cause);
    }

    public GroupDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
