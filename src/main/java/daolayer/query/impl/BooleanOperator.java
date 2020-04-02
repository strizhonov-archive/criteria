package daolayer.query.impl;

public enum BooleanOperator {
    AND(" AND "),
    OR(" OR ");

    private String value;

    BooleanOperator(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}