package daolayer.query.impl.predicate;

import daolayer.model.Column;
import daolayer.query.Predicate;
import daolayer.query.impl.ColumnCanNotBeFoundException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WhereBuilder<T> {

    private final Class<T> classType;
    private Predicate<T> currentRestriction;
    private BooleanOperator currentOperator;

    /**
     * Mapped field names and database column values
     * <p>
     * Keys - class field names
     * Values - database column names. Retrieved from <code>@Column</code> name annotation if present.
     * Class field name stored if there is no annotation
     *
     * @see daolayer.model.Column
     */
    private Map<String, String> fieldsMetaData = new HashMap<>();


    public WhereBuilder(final Class<T> classType) {
        this.classType = Objects.requireNonNull(classType);
        inflateFieldsMeta();
    }


    public WhereBuilder<T> equals(final String field, final String value) {
        return addBasicPredicate(field, value, ComparisonOperator.EQUALS);
    }


    public WhereBuilder<T> like(final String field, final String value) {
        return addBasicPredicate(field, value, ComparisonOperator.LIKE);
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
        if (currentAnnotation == null) {
            fieldsMetaData.put(fieldToStore.getName(), fieldToStore.getName());
        } else {
            fieldsMetaData.put(fieldToStore.getName(), currentAnnotation.name());
        }
    }


    private WhereBuilder<T> addBasicPredicate(final String field,
                                              final String value,
                                              final ComparisonOperator operator) {

        String columnName = fieldsMetaData.get(field);

        if (columnName == null) {
            throw new ColumnCanNotBeFoundException(
                    String.format("There's no table column found with field [%s] definition.", field));
        }

        BasicPredicate<T> createdRestriction
                = new BasicPredicate<>(columnName, value, operator);

        currentRestriction = currentRestriction == null
                ? createdRestriction
                : compositePredicate(createdRestriction);

        return this;
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
