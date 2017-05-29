package bchain.data;

/**
 * CHAIN from chaining protocol
 */
public abstract class ChainMessage implements Transportable {

    public static ChainFactory   factory;
    private       RequestMessage request;
    private       Client         client;

    private ChainMessage(RequestMessage request, Client client) {
        this.request = request;
        this.client = client;
    }

    /**
     * Generate new {@link AckMessage} for bChain cluster pipeline
     */
    public AckMessage makeAckMessage() {
        return AckMessage.ack(this);
    }

    /**
     * Generate new {@link ReplyMessage} for bChain cluster pipeline
     */
    public ReplyMessage makeReplyMessage() {
        // TODO: implement makeReplyMessage
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Client getClientInformation() {
        return client;
    }

    public static ChainMessage chain(RequestMessage request) {
        return factory.get(request);
    }

    public static abstract class ChainFactory {
        public abstract ChainMessage get(RequestMessage request);
    }
}
