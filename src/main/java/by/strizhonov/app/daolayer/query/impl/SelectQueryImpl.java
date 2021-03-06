package by.strizhonov.app.daolayer.query.impl;

import by.strizhonov.app.daolayer.model.Table;
import by.strizhonov.app.daolayer.query.Predicate;
import by.strizhonov.app.daolayer.query.SelectQuery;

import java.util.Objects;

public class SelectQueryImpl<T> implements SelectQuery<T> {

    private static final String SELECT_ALL_FROM_KEY_WORDS = "SELECT * FROM ";
    private static final String WHERE_KEY_WORD = " WHERE ";
    private static final String EMPTY = "";
    private final Class<T> classType;
    private Predicate<T> restriction;
    private String tableName;
    private String wherePlaceholder;


    public SelectQueryImpl(final Class<T> classType) {
        this.classType = classType;
        this.wherePlaceholder = EMPTY;
        extractTableName();
    }


    @Override
    public void where(final Predicate<T> restriction) {
        wherePlaceholder = WHERE_KEY_WORD;
        this.restriction = Objects.requireNonNull(restriction);
    }


    @Override
    public String toString() {
        return SELECT_ALL_FROM_KEY_WORDS +
                tableName +
                wherePlaceholder +
                restriction.toString();
    }


    /**
     * Retrieve table name from <code>@Table</code> annotation. Returns class name, if
     * annotation is absent.
     *
     * @see Table
     */
    private void extractTableName() {
        Table table = classType.getAnnotation(Table.class);
        this.tableName = table == null
                ? classType.getSimpleName().toLowerCase()
                : table.name();
    }


}
