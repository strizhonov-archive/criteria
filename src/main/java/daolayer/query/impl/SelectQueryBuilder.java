package daolayer.query.impl;

import daolayer.query.Predicate;
import daolayer.query.SelectQuery;
import daolayer.query.impl.predicate.WhereBuilder;

import java.util.Objects;

public class SelectQueryBuilder<T> {


    private final Class<T> classType;
    private SelectQuery<T> resultQuery;


    public SelectQueryBuilder(final Class<T> classType) {
        this.classType = Objects.requireNonNull(classType);
        this.resultQuery = new SelectQueryImpl<>(classType);
    }


    public SelectQueryBuilder<T> where(Predicate<T> predicate) {
        resultQuery.where(predicate);
        return this;
    }


    public WhereBuilder<T> createWhereBuilder() {
        return new WhereBuilder<>(classType);
    }


    public SelectQuery<T> build() {
        return resultQuery;
    }


}
