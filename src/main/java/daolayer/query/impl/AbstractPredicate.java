package daolayer.query.impl;

import daolayer.query.Predicate;

import java.util.Arrays;

public abstract class AbstractPredicate implements Predicate {

    @Override
    public Predicate or(final Predicate predicate) {
        return compoundPredicate(BooleanOperator.OR, this, predicate);
    }

    @Override
    public Predicate and(final Predicate predicate) {
        return compoundPredicate(BooleanOperator.AND, this, predicate);
    }

    private Predicate compoundPredicate(final BooleanOperator operator, final Predicate... predicates) {
        return new CompoundPredicate(operator, Arrays.asList(predicates));
    }

}
