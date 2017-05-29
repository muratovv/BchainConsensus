package bchain.data;

/**
 * ASK from chaining protocol
 */
public abstract class AckMessage implements Transportable {

    /**
     * Retrieve existed {@link ChainMessage}
     */
    public abstract ChainMessage retrieveChainMessage();
}
