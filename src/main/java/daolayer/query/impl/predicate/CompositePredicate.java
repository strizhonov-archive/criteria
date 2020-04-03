package daolayer.query.impl.predicate;

import daolayer.query.BinaryPredicate;
import daolayer.query.Predicate;
import util.StringWrapper;

import java.util.Objects;

public class CompositePredicate<T> extends AbstractPredicate<T> implements BinaryPredicate<T> {

    private final Predicate<T> leftRestriction;
    private final BooleanOperator operator;
    private final Predicate<T> rightRestriction;


    public CompositePredicate(final BooleanOperator operator, final Predicate<T> left, final Predicate<T> right) {
        this.operator = Objects.requireNonNull(operator);
        this.leftRestriction = Objects.requireNonNull(left);
        this.rightRestriction = Objects.requireNonNull(right);
    }


    @Override
    public Predicate<T> getLeftHandSide() {
        return leftRestriction;
    }


    @Override
    public Predicate<T> getRightHandSide() {
        return rightRestriction;
    }


    @Override
    public BooleanOperator getOperator() {
        return operator;
    }


    @Override
    public String toString() {
        return StringWrapper.wrapWithParentheses(leftRestriction.toString()) +
                operator.toString() +
                StringWrapper.wrapWithParentheses(rightRestriction.toString());
    }

}
