package daolayer.query;

import daolayer.query.impl.predicate.BooleanOperator;

public interface BinaryPredicate<T> extends LeafPredicate<T> {

    Predicate<T> getLeftHandSide();

    Predicate<T> getRightHandSide();

    BooleanOperator getOperator();

}
