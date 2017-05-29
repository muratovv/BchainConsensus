package bchain.data;

/**
 * CHAIN from chaining protocol
 */
public abstract class ChainMessage implements Transportable {

    /**
     * Generate new {@link AckMessage} for bChain cluster pipeline
     */
    public abstract AckMessage makeAckMessage();

    /**
     * Generate new {@link ReplyMessage} for bChain cluster pipeline
     */
    public abstract ReplyMessage makeReplyMessage();

    public Client getClientInformation() {
        // TODO: implement getClientInformation
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
