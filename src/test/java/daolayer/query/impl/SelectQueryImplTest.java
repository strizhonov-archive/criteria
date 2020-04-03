package daolayer.query.impl;

import daolayer.query.Predicate;
import daolayer.query.impl.predicate.BasicPredicate;
import daolayer.query.impl.predicate.CompositePredicate;
import daolayer.query.impl.predicate.BooleanOperator;
import daolayer.query.impl.predicate.ComparisonOperator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SelectQueryImplTest {


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenConstructorArgumentIsNull() {
        new SelectQueryImpl<>(null);
    }


    @Test
    public void shouldSetAndGetTwoSetParameters() {
        Predicate<TestEntity> firstChild
                = new BasicPredicate<>("int_field", "TEST_VALUE", ComparisonOperator.EQUALS);
        Predicate<TestEntity> secondChild
                = new BasicPredicate<>("stringTestField", "TEST_VALUE", ComparisonOperator.LIKE);
        Predicate<TestEntity> result
                = new CompositePredicate<>(BooleanOperator.AND, firstChild, secondChild);

        SelectQueryImpl<TestEntity> itemToTest = new SelectQueryImpl<>(TestEntity.class);
        itemToTest.where(result);

        Assert.assertEquals(2, itemToTest.getParameters().size());
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerAfterSettingNullPredicate() {
        SelectQueryImpl<TestEntity> itemToTest = new SelectQueryImpl<>(TestEntity.class);
        itemToTest.where(null);
    }

}
