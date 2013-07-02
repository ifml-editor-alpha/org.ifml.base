package org.ifml.base;

import javax.annotation.Nullable;

/**
 * Provides utility methods for {@link String}.
 */
public final class Strings2 {

    private Strings2() {
    }

    /**
     * Capitalizes a String changing the first letter to title case as per {@link Character#toTitleCase(char)}. No other letters are
     * changed.
     * 
     * <pre>
     * Strings2.capitalize(null)  = null
     * Strings2.capitalize("")    = ""
     * Strings2.capitalize("cat") = "Cat"
     * Strings2.capitalize("cAt") = "CAt"
     * </pre>
     * 
     * @param str
     *            the string to capitalize.
     * @return the capitalized string.
     */
    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuffer(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * Substitutes each {@code %s} in {@code template} with an argument. These are matched by position - the first {@code %s} gets
     * {@code args[0]}, etc. If there are more arguments than placeholders, the unmatched arguments will be appended to the end of the
     * formatted message in square braces.
     * 
     * @param template
     *            a string containing 0 or more {@code %s} placeholders.
     * @param args
     *            the arguments to be substituted into the message template. Arguments are converted to strings using
     *            {@link String#valueOf(Object)}. Arguments can be null.
     * @return the formatted string.
     */
    public static String simpleFormat(@Nullable String template, @Nullable Object... args) {
        template = String.valueOf(template); // null -> "null"

        // start substituting the arguments into the '%s' placeholders
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;
        int i = 0;
        while (i < args.length) {
            int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template.substring(templateStart, placeholderStart));
            builder.append(args[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template.substring(templateStart));

        // if we run out of placeholders, append the extra args in square braces
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);
            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }
            builder.append(']');
        }

        return builder.toString();
    }

}
