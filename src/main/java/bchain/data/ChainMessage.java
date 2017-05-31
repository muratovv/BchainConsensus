package bchain.data;

import java.util.Objects;

/**
 * CHAIN from chaining protocol
 */
public class ChainMessage implements Transportable {

    public static ChainFactory   factory;
    private       RequestMessage request;

    protected ChainMessage(RequestMessage request) {
        this.request = request;
    }

    public static ChainMessage chain(RequestMessage request) {
        return factory.get(request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequest());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChainMessage)) return false;
        ChainMessage that = (ChainMessage) o;
        return Objects.equals(getRequest(), that.getRequest());
    }

    public RequestMessage getRequest() {
        return request;
    }

    @Override
    public String toTransport() {
        // TODO: implement toTransport
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static abstract class ChainFactory {
        public abstract ChainMessage get(RequestMessage request);
    }
}
