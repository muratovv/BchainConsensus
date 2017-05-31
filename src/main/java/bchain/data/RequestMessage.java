package bchain.data;

import java.util.Objects;

/**
 * REQUEST from chaining protocol
 */
public abstract class RequestMessage extends Message {
    public Client client;

    @Override
    public int hashCode() {
        return Objects.hash(client);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestMessage)) return false;
        RequestMessage that = (RequestMessage) o;
        return Objects.equals(client, that.client);
    }

    public static abstract class RequestSerializeFactory {
        public abstract String serialize(RequestMessage request);
    }
}
