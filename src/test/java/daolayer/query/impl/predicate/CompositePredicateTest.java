package daolayer.query.impl.predicate;

import daolayer.model.Resume;
import daolayer.query.Predicate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CompositePredicateTest {

    private final Predicate<Resume> stub
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
        Predicate<Resume> firstChild
                = new BasicPredicate<>("current", "predicate", ComparisonOperator.EQUALS);
        Predicate<Resume> secondChild
                = new BasicPredicate<>("other", "predicate", ComparisonOperator.LIKE);
        Predicate<Resume> thirdChild
                = new BasicPredicate<>("another", "predicate", ComparisonOperator.LIKE);

        Predicate<Resume> itemToTest
                = new CompositePredicate<>(BooleanOperator.AND, firstChild, secondChild);

        Predicate<Resume> result = itemToTest.or(thirdChild);
        Assert.assertEquals(3, result.getParameters().size());
    }


    @Test
    public void shouldBuildUpAndPredicateAndGetParamsFromChildren() {
        Predicate<Resume> firstChild
                = new BasicPredicate<>("current", "predicate", ComparisonOperator.EQUALS);
        Predicate<Resume> secondChild
                = new BasicPredicate<>("other", "predicate", ComparisonOperator.LIKE);
        Predicate<Resume> thirdChild
                = new BasicPredicate<>("another", "predicate", ComparisonOperator.LIKE);

        Predicate<Resume> itemToTest
                = new CompositePredicate<>(BooleanOperator.AND, firstChild, secondChild);

        Predicate<Resume> result = itemToTest.and(thirdChild);
        Assert.assertEquals(3, result.getParameters().size());
    }

}
