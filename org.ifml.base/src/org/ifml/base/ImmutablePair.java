package org.ifml.base;

import com.google.common.base.Objects;

/**
 * A general purpose immutable pair of objects.
 * 
 * @param <A>
 *            type of the first object
 * @param <B>
 *            type of the second object
 */
public final class ImmutablePair<A, B> {

    /** First object. */
    public final A first;

    /** Second object. */
    public final B second;

    /**
     * Constructs a new pair.
     * 
     * @param first
     *            first object
     * @param second
     *            second object
     */
    private ImmutablePair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Obtains a pair.
     * 
     * @param <A>
     *            type of the first object
     * @param <B>
     *            type of the second object
     * @param first
     *            first object
     * @param second
     *            second object
     * @return constructed pair
     */
    public static <A, B> ImmutablePair<A, B> of(A first, B second) {
        return new ImmutablePair<A, B>(first, second);
    }

    /**
     * Returns {@code true} if the given pair is null or its components are both null.
     * 
     * @param pair
     *            a pair to check.
     * @return {@code true} if the given pair is null or its components are both null.
     */
    public static boolean isNull(ImmutablePair<?, ?> pair) {
        return (pair == null) || ((pair.first == null) && (pair.second == null));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ImmutablePair<?, ?>) {
            ImmutablePair<?, ?> that = (ImmutablePair<?, ?>) obj;
            return Objects.equal(first, that.first) && Objects.equal(second, that.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first) ^ Objects.hashCode(second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

}
