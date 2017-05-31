package bchain.data;

/**
 * CHAIN from chaining protocol
 */
public abstract class ChainMessage implements Transportable {

    public static ChainFactory   factory;
    private       RequestMessage request;

    protected ChainMessage(RequestMessage request) {
        this.request = request;
    }

    public static ChainMessage chain(RequestMessage request) {
        return factory.get(request);
    }

    public RequestMessage getRequest() {
        return request;
    }

    public static abstract class ChainFactory {
        public abstract ChainMessage get(RequestMessage request);
    }
}
