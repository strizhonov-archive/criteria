package daolayer.query.impl.predicate;

import daolayer.query.Predicate;
import util.StringWrapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BinaryPredicate<T> extends AbstractPredicate<T> {

    private final Predicate<T> leftRestriction;
    private final BooleanOperator operator;
    private final Predicate<T> rightRestriction;


    public BinaryPredicate(final BooleanOperator operator, final Predicate<T> left, final Predicate<T> right) {
        this.operator = Objects.requireNonNull(operator);
        this.leftRestriction = Objects.requireNonNull(left);
        this.rightRestriction = Objects.requireNonNull(right);
    }


    @SuppressWarnings(value = "unchecked")
    @Override
    public Set<PredicateParam> getParameters() {
        Set<PredicateParam> params = new HashSet<>();
        params.addAll((Collection<PredicateParam>) leftRestriction.getParameters());
        params.addAll((Collection<PredicateParam>) rightRestriction.getParameters());
        return params;
    }


    @Override
    public String toString() {
        return StringWrapper.wrapWithParentheses(leftRestriction.toString()) +
                operator.toString() +
                StringWrapper.wrapWithParentheses(rightRestriction.toString());
    }

}
