package daolayer.query.impl.predicate;

import daolayer.query.LeafPredicate;
import daolayer.query.Predicate;

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
