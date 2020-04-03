package by.strizhonov.app.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class StringWrapperTest {

    private String unwrapped = "string-to-test";

    @Test
    public void shouldWrapStringWithSingleQuotation() {
        String wrapped = StringWrapper.wrapWithSingleQuotation(unwrapped);
        Assert.assertTrue(wrapped.trim().startsWith("'"));
        Assert.assertEquals(wrapped.trim().lastIndexOf("'"), wrapped.trim().length() - 1);
    }


    @SuppressWarnings("all")
    @Test(expected = NullPointerException.class)
    public void quotationShouldThrowNullPointerSingle() {
        StringWrapper.wrapWithSingleQuotation(null);
    }


    @Test
    public void shouldWrapStringWithParentheses() {
        String wrapped = StringWrapper.wrapWithParentheses(unwrapped);
        Assert.assertTrue(wrapped.trim().startsWith("("));
        Assert.assertEquals(wrapped.trim().lastIndexOf(")"), wrapped.trim().length() - 1);
    }


    @SuppressWarnings("all")
    @Test(expected = NullPointerException.class)
    public void parenthesesShouldThrowNullPointer() {
        StringWrapper.wrapWithParentheses(null);
    }


}
