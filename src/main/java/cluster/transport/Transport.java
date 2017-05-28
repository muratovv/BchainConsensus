package cluster.transport;

import bchain.data.Node;

/**
 * {@link Transport} represents interconnection layers between nodes
 */
public interface Transport {
    void send(Node to, String message);

    void setMessageListener(MessageListener listener);
}
