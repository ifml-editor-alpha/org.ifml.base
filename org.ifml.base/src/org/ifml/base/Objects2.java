package org.ifml.base;

import com.google.common.base.Preconditions;

/**
 * Provides utility methods for {@link Object}.
 */
public final class Objects2 {

    private Objects2() {
    }

    /**
     * Casts an object to a specific class. Returns {@code null} if the object is not instance of the class.
     * 
     * @param <T>
     *            the type parameter.
     * @param obj
     *            the object to cast.
     * @param cl
     *            the target class.
     * @return {@code obj} casted to {@code cl} or {@code null} if {@code obj} is not instance of {@code cl}.
     */
    public static <T> T as(Object obj, Class<T> cl) {
        Preconditions.checkNotNull(cl);
        if (cl.isInstance(obj)) {
            return cl.cast(obj);
        } else {
            return null;
        }
    }

}
