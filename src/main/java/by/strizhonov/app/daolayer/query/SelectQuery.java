package by.strizhonov.app.daolayer.query;

public interface SelectQuery<T> {

    void where(Predicate<T> restriction);

}
