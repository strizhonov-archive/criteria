package daolayer.query;

import java.util.Set;

public interface SelectQuery<T> {

    SelectQuery<T> where(Predicate<T> restriction);

    Set<?> getParameters();
}
