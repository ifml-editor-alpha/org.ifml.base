package org.ifml.base;

import com.google.common.base.Ascii;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;

/**
 * Utility class for converting between various ASCII case formats, inspired by Guava {@link CaseFormat}.
 */
public enum WordFormat {

    /**
     * Hyphenated variable naming convention, e.g., "lower-hyphen".
     */
    LOWER_HYPHEN(CaseFormat.LOWER_HYPHEN),

    /**
     * C++ variable naming convention, e.g., "lower_underscore".
     */
    LOWER_UNDERSCORE(CaseFormat.LOWER_UNDERSCORE),

    /**
     * Java variable naming convention, e.g., "lowerCamel".
     */
    LOWER_CAMEL(CaseFormat.LOWER_CAMEL),

    /**
     * Java and C++ class naming convention, e.g., "UpperCamel".
     */
    UPPER_CAMEL(CaseFormat.UPPER_CAMEL),

    /**
     * Java and C++ constant naming convention, e.g., "UPPER_UNDERSCORE".
     */
    UPPER_UNDERSCORE(CaseFormat.UPPER_UNDERSCORE),

    /**
     * Blank separated upper words naming convention, e.g. "Upper Word Name"
     */
    CAPITALIZED_WORDS(null),

    /**
     * Blank separated lower words naming convention, e.g. "upper word name"
     */
    UNCAPITALIZED_WORDS(null),

    /**
     * Blank separated lower words (the first capitalized) naming convention, e.g. "Upper word name"
     */
    PHRASE(null);

    private CaseFormat caseFormat;

    WordFormat(CaseFormat caseFormat) {
        this.caseFormat = caseFormat;
    }

    /**
     * Converts the specified string from this format to the specified {@code format}. A "best effort" approach is taken; if {@code s}
     * does not conform to the assumed format, then the behavior of this method is undefined but we make a reasonable effort at
     * converting anyway.
     * 
     * @param format
     *            the target format.
     * @param s
     *            the string to convert.
     * @return the converted string.
     */
    public String to(WordFormat format, String s) {
        if (format == null) {
            throw new NullPointerException();
        }
        if (s == null) {
            throw new NullPointerException();
        }

        if (format == this) {
            return s;
        }
        switch (this) {
        case CAPITALIZED_WORDS:
        case UNCAPITALIZED_WORDS:
        case PHRASE:
            return UPPER_UNDERSCORE.to(format, Ascii.toUpperCase(s.replace(' ', '_')));
        default:
            return to(caseFormat, format, s);
        }
    }

    private String to(CaseFormat caseFormat, WordFormat wordFormat, String s) {
        switch (wordFormat) {
        case CAPITALIZED_WORDS:
            return capitalizeWords(caseFormat.to(CaseFormat.LOWER_UNDERSCORE, s).replace('_', ' '));
        case UNCAPITALIZED_WORDS:
            return caseFormat.to(CaseFormat.LOWER_UNDERSCORE, s).replace('_', ' ');
        case PHRASE:
            return Strings2.capitalize(Ascii.toLowerCase(caseFormat.to(CaseFormat.LOWER_UNDERSCORE, s)).replace('_', ' '));
        default:
            return caseFormat.to(wordFormat.caseFormat, s);
        }
    }

    private String capitalizeWords(String words) {
        int length = words.length();
        StringBuilder out = new StringBuilder(length);
        boolean firstOfWord = true;
        for (int i = 0; i < length; i++) {
            char c = words.charAt(i);
            if (firstOfWord) {
                firstOfWord = false;
                out.append(Ascii.toUpperCase(c));
            } else {
                out.append(c);
                if (CharMatcher.WHITESPACE.matches(c)) {
                    firstOfWord = true;
                }
            }
        }
        return out.toString();
    }

}
