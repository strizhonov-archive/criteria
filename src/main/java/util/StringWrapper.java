package util;

import java.util.Objects;

public class StringWrapper {

    private static final String FRONT_PARENTHESIS = " (";
    private static final String BACK_PARENTHESIS = ") ";
    private static final String SINGLE_QUOTATION = "'";


    public static String wrapWithParentheses(final String toWrap) {
        Objects.requireNonNull(toWrap);

        return FRONT_PARENTHESIS +
                toWrap +
                BACK_PARENTHESIS;
    }


    public static String wrapWithSingleQuotation(final String toWrap) {
        Objects.requireNonNull(toWrap);

        return SINGLE_QUOTATION +
                toWrap +
                SINGLE_QUOTATION;

    }


    private StringWrapper() {
        // To hide public
    }

}
