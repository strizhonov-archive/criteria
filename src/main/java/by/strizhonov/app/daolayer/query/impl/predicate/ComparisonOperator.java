package by.strizhonov.app.daolayer.query.impl.predicate;

public enum ComparisonOperator {
    LIKE(" LIKE "),
    EQUALS(" = ");

    private String value;

    ComparisonOperator(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}