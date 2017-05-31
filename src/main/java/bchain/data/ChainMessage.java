package bchain.data;

import java.util.Objects;

/**
 * CHAIN from chaining protocol
 */
public class ChainMessage {

    public static ChainSerializeFactory factory;
    public        RequestMessage        request;

    protected ChainMessage(RequestMessage request) {
        this.request = request;
    }

    public static ChainMessage chain(RequestMessage request) {
        return new ChainMessage(request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChainMessage)) return false;
        ChainMessage that = (ChainMessage) o;
        return Objects.equals(request, that.request);
    }

    public static abstract class ChainSerializeFactory {
        public abstract String serialize(ChainMessage chain);
    }
}
