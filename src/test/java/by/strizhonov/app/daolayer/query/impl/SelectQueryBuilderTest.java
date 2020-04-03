package by.strizhonov.app.daolayer.query.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SelectQueryBuilderTest {


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenConstructorArgumentIsNull() {
        new SelectQueryBuilder<>(null);
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerAfterSettingNullPredicate() {
        SelectQueryBuilder<TestEntity> itemToTest = new SelectQueryBuilder<>(TestEntity.class);
        itemToTest.where(null);
    }

}
