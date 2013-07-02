package org.ifml.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.ifml.base.WordFormat;

/**
 * Indicates the custom text being returned when the annotated enumeration value is converted to a specific {@link WordFormat}.
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface WordFormatText {

    /**
     * The target {@link WordFormat}.
     */
    WordFormat[] format();

    /**
     * The custom text.
     */
    String[] value();

}
