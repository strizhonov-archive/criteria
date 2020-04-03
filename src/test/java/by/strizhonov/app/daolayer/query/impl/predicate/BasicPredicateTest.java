package by.strizhonov.app.daolayer.query.impl.predicate;

import by.strizhonov.app.daolayer.query.BinaryPredicate;
import by.strizhonov.app.daolayer.query.Predicate;
import by.strizhonov.app.daolayer.query.impl.TestEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BasicPredicateTest {


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenFirstConstructorArgumentIsNull() {
        new BasicPredicate<>(null, "TEST_VALUE", ComparisonOperator.EQUALS);
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenSecondConstructorArgumentIsNull() {
        new BasicPredicate<>("stringTestField", null, ComparisonOperator.EQUALS);
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenThirdConstructorArgumentIsNull() {
        new BasicPredicate<>("int_field", "TEST_VALUE", null);
    }


    @Test
    public void shouldBuildUpOrPredicate() {
        Predicate<TestEntity> initial
                = new BasicPredicate<>("stringTestField", "predicate", ComparisonOperator.EQUALS);
        Predicate<TestEntity> result = initial
                .or(new BasicPredicate<>("int_field", "predicate", ComparisonOperator.LIKE));


        Assert.assertEquals(BooleanOperator.OR, ((BinaryPredicate<TestEntity>) result).getOperator());
    }


    @Test
    public void shouldBuildUpAndPredicate() {
        Predicate<TestEntity> initial
                = new BasicPredicate<>("stringTestField", "predicate", ComparisonOperator.EQUALS);
        Predicate<TestEntity> result = initial
                .and(new BasicPredicate<>("int_field", "predicate", ComparisonOperator.LIKE));

        Assert.assertEquals(BooleanOperator.AND, ((BinaryPredicate<TestEntity>) result).getOperator());
    }

}
