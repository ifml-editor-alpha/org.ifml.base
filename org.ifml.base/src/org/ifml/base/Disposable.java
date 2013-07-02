package org.ifml.base;

/**
 * The generic interface of objects providing a {@link #dispose} method able to release resources.
 */
public interface Disposable {

    /** Disposes the resources retained by this object. */
    void dispose();

}
