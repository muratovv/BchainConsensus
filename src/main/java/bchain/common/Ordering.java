package bchain.common;

import bchain.data.Node;

import java.util.List;

/**
 * Ordering of nodes in cluster
 */
public interface Ordering {

    /**
     * Set cluster order
     *
     * @param list of nodes in cluster order
     *
     * @return this
     */
    FairOrdering setOrder(List<Node> list);

    /**
     * Set node information about current jvm instance
     *
     * @param node
     */
    void setMyNode(Node node);

    /**
     * Get next node in order
     */
    Node successor();

    /**
     * Get previous node in order
     */
    Node predecessor();

    /**
     * Get mirrored node from non-validation set
     */
    Node getMirrorFromNodeNonValidationSet();

    /**
     * Is current leader?
     */
    boolean iAmLeader();

    /**
     * Is current node is proxy tail?
     */
    boolean iAmProxyTail();

    boolean iAmFromValidateSet();
}
