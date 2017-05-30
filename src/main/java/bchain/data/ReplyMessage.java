package bchain.data;

/**
 * REPLY from chaining protocol
 */
public abstract class ReplyMessage implements Transportable {

    public static ReplyFactory factory;

    /**
     * Generate new {@link ReplyMessage} for bChain cluster pipeline
     */
    public static ReplyMessage reply(ChainMessage chain) {
        return factory.get(chain);
    }

    public abstract class ReplyFactory {
        public abstract ReplyMessage get(ChainMessage chain);
    }
}
