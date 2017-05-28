package cluster.transport;

import bchain.data.Node;

/**
 * Subscriber of income messages in interconnection communication
 */
public interface MessageListener {
    void onRecieve(Node from, String message);
}
