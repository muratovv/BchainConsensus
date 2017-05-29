package cluster.transport;

import bchain.data.Node;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for organize transport via network
 */
public class LanTransport implements Transport {
    private Node currentNode;
    private Map<Node, CharBuffer> buffers = new HashMap<>();

    public LanTransport(List<Node> allNodes, Node currentNode) {
    }

    @Override
    public void send(Node to, String message) {

    }

    @Override
    public void setMessageListener(MessageListener listener) {

    }
}
