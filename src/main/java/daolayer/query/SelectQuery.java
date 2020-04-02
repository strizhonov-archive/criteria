package daolayer.query;

public interface SelectQuery {

    void setTableName(String tableName);

    void setPredicate(Predicate predicate);

    Predicate getPredicate();

}
