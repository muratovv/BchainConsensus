package bchain.data;

import java.util.Objects;

/**
 * ASK from chaining protocol
 */
public class AckMessage {

    public static AckSerializeFactory factory;
    private       ChainMessage        chain;

    public AckMessage(ChainMessage chain) {
        this.chain = chain;
    }

    public static AckMessage ack(ChainMessage chain) {
        return new AckMessage(chain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AckMessage)) return false;
        AckMessage that = (AckMessage) o;
        return Objects.equals(chain, that.chain);
    }

    /**
     * Retrieve existed {@link ChainMessage}
     */
    public ChainMessage retrieveChainMessage() {
        return chain;
    }

    public static abstract class AckSerializeFactory {
        public abstract String serialize(AckMessage ack);
    }
}
