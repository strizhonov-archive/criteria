package daolayer.query;

import daolayer.model.Column;
import daolayer.query.impl.BasicPredicate;
import daolayer.query.impl.BooleanOperator;
import daolayer.query.impl.ComparisonOperator;
import daolayer.query.impl.EmptyPredicate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class WherePredicateBuilder {

    private Predicate predicate;
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


    public WherePredicateBuilder(final Class<?> clazz) {
        predicate = new EmptyPredicate();
        inflateFieldsMeta(clazz);
    }


    public WherePredicateBuilder equals(final String field, final String value) {
        BasicPredicate basicPredicate = new BasicPredicate(field, value, ComparisonOperator.EQUALS);
        predicate = predicate.isEmpty() ? basicPredicate : compoundPredicate(basicPredicate);
        return this;
    }


    public WherePredicateBuilder like(final String field, final String value) {
        BasicPredicate basicPredicate = new BasicPredicate(field, value, ComparisonOperator.LIKE);
        predicate = predicate.isEmpty() ? basicPredicate : compoundPredicate(basicPredicate);
        return this;
    }


    public WherePredicateBuilder and() {
        currentOperator = BooleanOperator.AND;
        return this;
    }

    public WherePredicateBuilder or() {
        currentOperator = BooleanOperator.OR;
        return this;
    }


    public Sel build() {
        return predicate;
    }


    private void inflateFieldsMeta(final Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
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


    private Predicate compoundPredicate(final BasicPredicate basicPredicate) {
        switch (currentOperator) {
            case AND:
                return predicate.and(basicPredicate);
            case OR:
                return predicate.or(basicPredicate);
            default:
                throw new IllegalStateException(
                        String.format("There is no value for [%s] operator.", currentOperator));
        }
    }


}
