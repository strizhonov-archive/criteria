package daolayer.query.impl;

import daolayer.query.Predicate;
import daolayer.query.SelectQuery;
import daolayer.query.impl.predicate.BasicPredicate;
import daolayer.query.impl.predicate.BooleanOperator;
import daolayer.query.impl.predicate.ComparisonOperator;
import daolayer.query.impl.predicate.CompositePredicate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SelectQueryBuilderTest {


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenConstructorArgumentIsNull() {
        new SelectQueryBuilder<>(null);
    }


    @Test
    public void shouldSetSpecifiedPredicate() {
        Predicate<TestEntity> firstChild
                = new BasicPredicate<>("int_field", "TEST_VALUE", ComparisonOperator.EQUALS);
        Predicate<TestEntity> secondChild
                = new BasicPredicate<>("stringTestField", "TEST_VALUE", ComparisonOperator.LIKE);
        Predicate<TestEntity> result
                = new CompositePredicate<>(BooleanOperator.AND, firstChild, secondChild);

        SelectQueryBuilder<TestEntity> itemToTest = new SelectQueryBuilder<>(TestEntity.class);
        SelectQuery<TestEntity> builtQuery = itemToTest.where(result).build();

        Assert.assertEquals(2, builtQuery.getParameters().size());
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerAfterSettingNullPredicate() {
        SelectQueryBuilder<TestEntity> itemToTest = new SelectQueryBuilder<>(TestEntity.class);
        itemToTest.where(null);
    }

}
