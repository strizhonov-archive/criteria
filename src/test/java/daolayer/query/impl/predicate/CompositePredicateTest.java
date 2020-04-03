package daolayer.query.impl.predicate;

import daolayer.query.BinaryPredicate;
import daolayer.query.Predicate;
import daolayer.query.impl.TestEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CompositePredicateTest {

    private final Predicate<TestEntity> stub
            = new BasicPredicate<>("LEFT", "RIGHT", ComparisonOperator.EQUALS);

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenFirstConstructorArgumentIsNull() {
        new CompositePredicate<>(null, stub, stub);
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenSecondConstructorArgumentIsNull() {
        new CompositePredicate<>(BooleanOperator.OR, null, stub);
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenThirdConstructorArgumentIsNull() {
        new CompositePredicate<>(BooleanOperator.AND, stub, null);
    }


    @Test
    public void shouldBuildUpOrPredicate() {
        Predicate<TestEntity> firstChild
                = new BasicPredicate<>("current", "predicate", ComparisonOperator.EQUALS);
        Predicate<TestEntity> secondChild
                = new BasicPredicate<>("other", "predicate", ComparisonOperator.LIKE);
        Predicate<TestEntity> thirdChild
                = new BasicPredicate<>("another", "predicate", ComparisonOperator.LIKE);

        Predicate<TestEntity> itemToTest
                = new CompositePredicate<>(BooleanOperator.AND, firstChild, secondChild);

        BinaryPredicate<TestEntity> result = (BinaryPredicate<TestEntity>) itemToTest.or(thirdChild);
        Assert.assertEquals(BooleanOperator.OR, result.getOperator());
    }


    @Test
    public void shouldBuildUpAndPredicate() {
        Predicate<TestEntity> firstChild
                = new BasicPredicate<>("current", "predicate", ComparisonOperator.EQUALS);
        Predicate<TestEntity> secondChild
                = new BasicPredicate<>("other", "predicate", ComparisonOperator.LIKE);
        Predicate<TestEntity> thirdChild
                = new BasicPredicate<>("another", "predicate", ComparisonOperator.LIKE);

        Predicate<TestEntity> itemToTest
                = new CompositePredicate<>(BooleanOperator.AND, firstChild, secondChild);

        BinaryPredicate<TestEntity> result = (BinaryPredicate<TestEntity>) itemToTest.and(thirdChild);
        Assert.assertEquals(BooleanOperator.AND, result.getOperator());
    }

}
