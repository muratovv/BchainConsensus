package bchain.common;

import deferred_queue.core.DeferredQueue;
import deferred_queue.core.Delay;

/**
 * Implementation of timer based on machine time
 */
public class FairTimer implements Timer {

    private DeferredQueue<Void> queue = new DeferredQueue<>();
    private Delay    currentDelay;
    private Delegate delegate;

    @Override
    public FairTimer setTime(Delay delay) {
        this.currentDelay = delay;
        return this;
    }

    @Override
    public FairTimer setDelegate(Delegate delegate) {
        this.delegate = delegate;
        queue.setOnTimeExpiredCallback(aVoid -> delegate.apply());
        return this;
    }

    @Override
    public void start() {
        queue.insert(null, currentDelay);
    }

    @Override
    public void deny() {
        queue.forcePull();
    }
}
