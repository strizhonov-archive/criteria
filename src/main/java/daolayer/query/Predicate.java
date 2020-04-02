package daolayer.query;

/**
 * Root interface for Objected-oriented SQL where-clause analog.
 */
public interface Predicate {

    /**
     * Check whether predicate contains any restrictions or not.
     *
     * @return boolean check result.
     */
    boolean isEmpty();

    /**
     * Compound current and passed predicates with AND logic
     *
     * @param predicate to add
     * @return compounded predicate
     */
    Predicate and(Predicate predicate);

    /**
     *  Compound current and passed predicates with OR logic
     *
     * @param predicate to add
     * @return compounded predicate
     */
    Predicate or(Predicate predicate);

}
