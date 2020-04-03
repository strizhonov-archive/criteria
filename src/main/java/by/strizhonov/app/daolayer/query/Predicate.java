package by.strizhonov.app.daolayer.query;

public interface Predicate<T> {

    Predicate<T> or(Predicate<T> other);

    Predicate<T> and(Predicate<T> other);

}
