package by.strizhonov.app.daolayer.query.impl;

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
