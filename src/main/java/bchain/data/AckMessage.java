package bchain.data;

/**
 * ASK from chaining protocol
 */
public abstract class AckMessage implements Transportable {

    public static AckFactory   factory;
    private       ChainMessage chain;

    public AckMessage(ChainMessage chain) {
        this.chain = chain;
    }

    public static AckMessage ack(ChainMessage chain) {
        return factory.get(chain);
    }

    /**
     * Retrieve existed {@link ChainMessage}
     */
    public ChainMessage retrieveChainMessage() {
        return chain;
    }

    public static abstract class AckFactory {
        public abstract AckMessage get(ChainMessage chain);
    }
}
