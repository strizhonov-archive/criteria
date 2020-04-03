package daolayer.query;

import java.util.Set;

public interface SelectQuery<T> {

    void where(Predicate<T> restriction);

    Set<?> getParameters();
}
