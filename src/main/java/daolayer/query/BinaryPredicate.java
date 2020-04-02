package daolayer.query;

import daolayer.query.impl.ComparisonOperator;

/**
 * Predicate, consisting of two plain predicates inside and operator between them.
 *
 * @param <NAME>  type of params name in plain predicate
 * @param <VALUE> type of params value in plain predicate
 */
public interface BinaryPredicate<NAME, VALUE> extends Predicate {

    NAME getLeftHandSide();

    VALUE getRightHandSide();

    ComparisonOperator getOperator();

}
