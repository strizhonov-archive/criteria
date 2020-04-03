package daolayer.query.impl.predicate;

public class ColumnCanNotBeFoundException extends RuntimeException {

    public ColumnCanNotBeFoundException(final String message) {
        super(message);
    }

    public ColumnCanNotBeFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
