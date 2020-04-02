package daolayer.query.impl;

import daolayer.query.Predicate;
import util.StringWrapper;

import java.util.List;
import java.util.Objects;

public class CompoundPredicate extends AbstractPredicate implements Predicate {

    private final static String EMPTY = "";
    private final BooleanOperator operator;
    private final List<Predicate> predicates;

    public CompoundPredicate(final BooleanOperator operator, final List<Predicate> predicates) {
        this.operator = Objects.requireNonNull(operator);
        this.predicates = Objects.requireNonNull(predicates);
    }

    public BooleanOperator getOperator() {
        return operator;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    @Override
    public boolean isEmpty() {
        for (Predicate current : predicates) {
            if (!current.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return processEmptyPredicates();
        } else {
            return processNonEmptyPredicates();
        }
    }

    private String processEmptyPredicates() {
        return EMPTY;
    }

    private String processNonEmptyPredicates() {
        StringBuilder sb = new StringBuilder();
        processFirstPredicate(sb);

        if (predicates.size() > 1) {
            processOtherPredicates(sb);
        }
        return StringWrapper.wrapWithParentheses(sb.toString());
    }

    private void processFirstPredicate(final StringBuilder sb) {
        sb.append(predicates.get(0).toString());
    }

    private void processOtherPredicates(final StringBuilder sb) {
        for (int i = 1; i < predicates.size(); i++) {
            Predicate current = predicates.get(i);
            String operatorPredicate = operator.getValue() + current.toString();
            sb.append(operatorPredicate);
        }
    }


}
