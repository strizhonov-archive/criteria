package daolayer.query.impl.predicate;

import util.StringWrapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BasicPredicate<T> extends AbstractPredicate<T> {


    private final String leftSide;
    private final String rightSide;
    private final ComparisonOperator operator;


    public BasicPredicate(final String leftSide, final String rightSide, final ComparisonOperator operator) {
        this.leftSide = Objects.requireNonNull(leftSide);
        this.rightSide = Objects.requireNonNull(rightSide);
        this.operator = Objects.requireNonNull(operator);
    }


    @Override
    public Set<PredicateParam> getParameters() {
        return new HashSet<>(Collections.singletonList(
                new PredicateParam(leftSide, operator, rightSide)));
    }


    @Override
    public String toString() {
        return leftSide +
                operator.getValue() +
                StringWrapper.wrapWithSingleQuotation(rightSide);
    }


}
