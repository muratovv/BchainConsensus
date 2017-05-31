package bchain.data;

/**
 * Class store data about client node
 */
public class Client {
    // TODO 30.05.17 Add address

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Client;
    }
}
