package by.strizhonov.app.daolayer.query.impl;

public class ColumnCanNotBeFoundException extends RuntimeException {

    public ColumnCanNotBeFoundException(final String message) {
        super(message);
    }

    public ColumnCanNotBeFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
