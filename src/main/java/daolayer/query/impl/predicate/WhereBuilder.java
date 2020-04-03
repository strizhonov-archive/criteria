package daolayer.query.impl.predicate;

import daolayer.model.Column;
import daolayer.query.Predicate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class WhereBuilder<T> {

    private final Class<T> classType;
    private Predicate<T> currentRestriction;
    private BooleanOperator currentOperator;

    /**
     * Mapped field names and database column values
     * Keys - class field names
     * Values - field <code>@Column name()</code> values
     * <p>
     * If field is not annotated with @Column - it skipped
     *
     * @see daolayer.model.Column
     */
    private Map<String, String> fieldsMetaData = new HashMap<>();


    public WhereBuilder(final Class<T> classType) {
        this.classType = classType;
        inflateFieldsMeta();
    }


    public WhereBuilder<T> equals(final String field, final String value) {
        BasicPredicate<T> createdRestriction
                = new BasicPredicate<T>(fieldsMetaData.get(field), value, ComparisonOperator.EQUALS);

        currentRestriction = currentRestriction == null
                ? createdRestriction
                : compositePredicate(createdRestriction);

        return this;
    }


    public WhereBuilder<T> like(final String field, final String value) {
        BasicPredicate<T> createdRestriction
                = new BasicPredicate<T>(fieldsMetaData.get(field), value, ComparisonOperator.LIKE);

        currentRestriction = currentRestriction == null
                ? createdRestriction
                : compositePredicate(createdRestriction);

        return this;
    }


    public WhereBuilder<T> and() {
        currentOperator = BooleanOperator.AND;
        return this;
    }


    public WhereBuilder<T> or() {
        currentOperator = BooleanOperator.OR;
        return this;
    }


    public Predicate<T> build() {
        return currentRestriction;
    }


    private void inflateFieldsMeta() {
        Field[] declaredFields = classType.getDeclaredFields();
        for (Field current : declaredFields) {
            storeFieldData(current);
        }
    }


    private void storeFieldData(final Field fieldToStore) {
        Column currentAnnotation = fieldToStore.getAnnotation(Column.class);
        if (currentAnnotation != null) {
            fieldsMetaData.put(fieldToStore.getName(), currentAnnotation.name());
        }
    }


    private Predicate<T> compositePredicate(final BasicPredicate<T> basicPredicate) {
        switch (currentOperator) {
            case AND:
                return currentRestriction.and(basicPredicate);
            case OR:
                return currentRestriction.or(basicPredicate);
            default:
                throw new IllegalStateException(
                        String.format("There is no value for [%s] operator.", currentOperator));
        }
    }


}
