package bchain.data;

import java.util.Objects;

/**
 * REQUEST from chaining protocol
 */
public abstract class RequestMessage extends Message implements Transportable {
    private Client client;

    public Client getClientInformation() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestMessage)) return false;
        RequestMessage that = (RequestMessage) o;
        return Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client);
    }

    @Override
    public String toTransport() {
        // TODO: implement toTransport
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
