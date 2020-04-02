package daolayer.query.impl;

import daolayer.query.SelectQuery;
import daolayer.query.Predicate;

import java.util.Objects;

public class SelectQueryImpl implements SelectQuery {

    private static final String SELECT_ALL_FROM_KEY_WORDS = "SELECT * FROM ";
    private static final String WHERE_KEY_WORD = " WHERE ";
    private static final String EMPTY = "";
    private Predicate predicate;
    private String tableName;
    private String wherePlaceholder;

    public SelectQueryImpl() {
        this.wherePlaceholder = EMPTY;
    }

    @Override
    public void setTableName(final String tableName) {
        this.tableName = Objects.requireNonNull(tableName);
    }

    @Override
    public void setPredicate(final Predicate predicate) {
        this.predicate = Objects.requireNonNull(predicate);
        if (!predicate.isEmpty()) {
            wherePlaceholder = WHERE_KEY_WORD;
        }
    }

    @Override
    public Predicate getPredicate() {
        return predicate;
    }

    @Override
    public String toString() {
        return SELECT_ALL_FROM_KEY_WORDS +
                tableName +
                wherePlaceholder +
                predicate.toString();
    }


}
