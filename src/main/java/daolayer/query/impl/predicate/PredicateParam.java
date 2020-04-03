package daolayer.query.impl.predicate;

import java.io.Serializable;
import java.util.Objects;

public class PredicateParam implements Serializable {

    private String param;
    private ComparisonOperator operator;
    private String value;


    public PredicateParam() {
        // Empty bean constructor
    }


    public PredicateParam(final String param, final ComparisonOperator operator, final String value) {
        this.param = param;
        this.operator = operator;
        this.value = value;
    }


    public String getParam() {
        return param;
    }

    public void setParam(final String param) {
        this.param = param;
    }

    public ComparisonOperator getOperator() {
        return operator;
    }

    public void setOperator(final ComparisonOperator operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PredicateParam that = (PredicateParam) o;
        return Objects.equals(param, that.param) &&
                operator == that.operator &&
                Objects.equals(value, that.value);
    }


    @Override
    public int hashCode() {
        return Objects.hash(param, operator, value);
    }


    @Override
    public String toString() {
        return "PredicateParam{" +
                "param='" + param + '\'' +
                ", operator=" + operator +
                ", value='" + value + '\'' +
                '}';
    }

}
