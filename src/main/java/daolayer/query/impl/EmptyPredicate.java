package daolayer.query.impl;

import daolayer.query.Predicate;

public class EmptyPredicate extends AbstractPredicate implements Predicate {

    @Override
    public boolean isEmpty() {
        return true;
    }

}
