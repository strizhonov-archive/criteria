package daolayer.query.impl;

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
}