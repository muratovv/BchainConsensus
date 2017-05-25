package bchain.common;

import deferred_queue.core.Delay;

/**
 * Timer interface for invocation {@link Delegate} after time passed
 */
public interface Timer {

    /**
     * Set time for wait
     */
    Timer setTime(Delay delay);

    /**
     * Set delegate for invocation
     */
    Timer setDelegate(Delegate delegate);

    /**
     * Start timer
     */
    void start();

    /**
     * Stop timer
     */
    void deny();
}
