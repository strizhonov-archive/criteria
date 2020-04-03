package daolayer.query.impl.predicate;

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

    @Override
    public String toString() {
        return value;
    }
}