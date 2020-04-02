package daolayer.query.impl;

import daolayer.query.BinaryPredicate;
import util.StringWrapper;

import java.util.Objects;

public class BasicPredicate extends AbstractPredicate implements BinaryPredicate<String, String> {

    private final String leftSide;
    private final String rightSide;
    private final ComparisonOperator operator;

    public BasicPredicate(final String leftSide, final String rightSide, final ComparisonOperator operator) {
        this.leftSide = Objects.requireNonNull(leftSide);
        this.rightSide = Objects.requireNonNull(rightSide);
        this.operator = Objects.requireNonNull(operator);
    }

    @Override
    public String getLeftHandSide() {
        return leftSide;
    }

    @Override
    public String getRightHandSide() {
        return rightSide;
    }

    @Override
    public ComparisonOperator getOperator() {
        return operator;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return leftSide +
                operator.getValue() +
                StringWrapper.wrapWithSingleQuotation(rightSide);
    }

}
