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
