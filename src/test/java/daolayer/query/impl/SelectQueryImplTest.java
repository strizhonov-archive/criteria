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


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerAfterSettingNullPredicate() {
        SelectQueryImpl<TestEntity> itemToTest = new SelectQueryImpl<>(TestEntity.class);
        itemToTest.where(null);
    }

}
