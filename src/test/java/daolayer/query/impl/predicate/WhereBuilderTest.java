package daolayer.query.impl.predicate;

import daolayer.query.Predicate;
import daolayer.query.impl.TestEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WhereBuilderTest {

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenConstructorArgIsNull() {
        new WhereBuilder<>(null);
    }

    @Test
    public void shouldCreateOneParamPredicate() {
        Predicate<TestEntity> result = new WhereBuilder<>(TestEntity.class)
                .equals("intTestField", "5")
                .build();

        Assert.assertEquals(1, result.getParameters().size());
    }

    @Test
    public void shouldCreateTwoParamPredicate() {
        Predicate<TestEntity> result
                = new WhereBuilder<>(TestEntity.class)
                .equals("stringTestField", "TEST_VALUE")
                .and()
                .like("intTestField", "%55")
                .build();

        Assert.assertEquals(2, result.getParameters().size());
    }

    @Test
    public void shouldCreateThreeParamPredicate() {
        Predicate<TestEntity> result
                = new WhereBuilder<>(TestEntity.class)
                .equals("intTestField", "TEST_VALUE")
                .and()
                .like("intTestField", "SECOND_TEST_VALUE")
                .or()
                .equals("stringTestField", "THIRD_TEST_VALUE")
                .build();

        Assert.assertEquals(3, result.getParameters().size());
    }

    @SuppressWarnings("all")
    @Test(expected = RuntimeException.class)
    public void shouldThrowUncheckedExceptionFromEquals() {
        new WhereBuilder<>(TestEntity.class)
                .equals("NON_EXISTING_FIELD", "TEST_VALUE");
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowUncheckedExceptionFromLike() {
        new WhereBuilder<>(TestEntity.class)
                .like("NON_EXISTING_FIELD", "TEST_VALUE");
    }

}
