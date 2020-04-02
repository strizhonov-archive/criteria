package daolayer.query;

import daolayer.model.Table;
import daolayer.query.impl.SelectQueryImpl;

public class SelectQueryBuilder {

    private final SelectQuery resultQuery;
    private Class<?> processedClass;
    private WherePredicateBuilder whereBuilder;


    public SelectQueryBuilder() {
        this.resultQuery = new SelectQueryImpl();
    }


    public SelectQueryBuilder select() {
        this.processedClass = clazz;

        String table = getTable(clazz);
        resultQuery.setTableName(table);

        return this;
    }


    public WherePredicateBuilder where() {
        whereBuilder = new WherePredicateBuilder(processedClass);
        return whereBuilder;
    }


    public SelectQuery build() {
        resultQuery.setPredicate(whereBuilder.build());
        return resultQuery;
    }

    public SelectQuery build() {
        resultQuery.setPredicate(whereBuilder.build());
        return resultQuery;
    }

    /**
     * Retrieve table name from <code>@Table</code> annotation. Returns class name, if
     * annotation is absent.
     *
     * @param clazz to process
     * @return table name
     * @see Table
     */
    private String getTable(final Class<?> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        return table == null
                ? clazz.getSimpleName().toLowerCase()
                : table.name();
    }

}
