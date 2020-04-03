package by.strizhonov.app.daolayer.query;

import by.strizhonov.app.daolayer.query.impl.predicate.BooleanOperator;

public interface BinaryPredicate<T> extends LeafPredicate<T> {

    Predicate<T> getLeftHandSide();

    Predicate<T> getRightHandSide();

    BooleanOperator getOperator();

}
