package bchain.data;

/**
 * REQUEST from chaining protocol
 */
public abstract class RequestMessage implements Transportable {
    private Client client;

    public Client getClientInformation() {
        return client;
    }
}
