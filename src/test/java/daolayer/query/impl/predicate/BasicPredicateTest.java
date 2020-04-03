package daolayer.query.impl.predicate;

import daolayer.query.Predicate;
import daolayer.query.impl.TestEntity;
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
    public void shouldGetSingleParameter() {
        BasicPredicate<TestEntity> itemToTest
                = new BasicPredicate<>("stringTestField", "TEST_VALUE", ComparisonOperator.LIKE);

        Assert.assertEquals(1, itemToTest.getParameters().size());
    }


    @Test
    public void shouldBuildUpOrPredicate() {
        Predicate<TestEntity> initial
                = new BasicPredicate<>("stringTestField", "predicate", ComparisonOperator.EQUALS);
        Predicate<TestEntity> result = initial
                .or(new BasicPredicate<>("int_field", "predicate", ComparisonOperator.LIKE));

        Assert.assertEquals(2, result.getParameters().size());
    }


    @Test
    public void shouldBuildUpAndPredicate() {
        Predicate<TestEntity> initial
                = new BasicPredicate<>("stringTestField", "predicate", ComparisonOperator.EQUALS);
        Predicate<TestEntity> result = initial
                .and(new BasicPredicate<>("int_field", "predicate", ComparisonOperator.LIKE));

        Assert.assertEquals(2, result.getParameters().size());
    }

}
