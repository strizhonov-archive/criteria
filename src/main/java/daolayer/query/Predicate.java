package daolayer.query;

import java.util.Set;

public interface Predicate<T> {

    Predicate<T> or(Predicate<T> other);

    Predicate<T> and(Predicate<T> other);

    Set<?> getParameters();

}
