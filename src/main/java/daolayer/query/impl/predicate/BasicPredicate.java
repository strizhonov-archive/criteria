package daolayer.query.impl.predicate;

import util.StringWrapper;

import java.util.Objects;

public class BasicPredicate<T> extends AbstractPredicate<T> {


    private final String leftSide;
    private final String rightSide;
    private final ComparisonOperator operator;


    public BasicPredicate(final String leftSide, final String rightSide, final ComparisonOperator operator) {
        this.leftSide = Objects.requireNonNull(leftSide);
        this.rightSide = Objects.requireNonNull(rightSide);
        this.operator = Objects.requireNonNull(operator);
    }


    public String getLeftSide() {
        return leftSide;
    }


    public String getRightSide() {
        return rightSide;
    }


    public ComparisonOperator getOperator() {
        return operator;
    }


    @Override
    public String toString() {
        return leftSide +
                operator.getValue() +
                StringWrapper.wrapWithSingleQuotation(rightSide);
    }

}
