package by.strizhonov.app.daolayer.query.impl.predicate;

import by.strizhonov.app.daolayer.query.LeafPredicate;
import by.strizhonov.app.daolayer.query.Predicate;

public abstract class AbstractPredicate<T> implements LeafPredicate<T> {


    @Override
    public Predicate<T> or(final Predicate<T> other) {
        return new CompositePredicate<>(BooleanOperator.OR, this, other);
    }


    @Override
    public Predicate<T> and(final Predicate<T> other) {
        return new CompositePredicate<>(BooleanOperator.AND, this, other);
    }

}
