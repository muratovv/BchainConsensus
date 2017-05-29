package bchain.data;

/**
 * REQUEST from chaining protocol
 */
public abstract class RequestMessage implements Transportable {
    /**
     * Generate new {@link ChainMessage} for bChain cluster pipeline
     */
    public abstract ChainMessage makeChainMessage();
}
