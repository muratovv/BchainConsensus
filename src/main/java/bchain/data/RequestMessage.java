package bchain.data;

/**
 * REQUEST from chaining protocol
 */
public abstract class RequestMessage extends Message implements Transportable {
    private Client client;

    public Client getClientInformation() {
        return client;
    }
}
