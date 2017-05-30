package bchain.common;

import bchain.data.Node;

import java.util.List;

/**
 * Builder for {@link Ordering}
 */
public interface OrderingBuilder {

    /**
     * Set cluster order
     *
     * @param list of nodes in cluster order
     *
     * @return this
     */
    OrderingBuilder setOrder(List<Node> list);

    /**
     * Set node information about current jvm instance
     *
     * @return this
     */
    OrderingBuilder setMyNode(Node node);

    /**
     * Build new instance with specified parameters
     */
    Ordering build();
}
