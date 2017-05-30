package bchain.common;

import bchain.data.Node;

/**
 * Ordering of nodes in cluster
 */
public interface Ordering {
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

    /**
     * Is current node from validate set
     */
    boolean iAmFromValidateSet();

    /**
     * Get leader node
     */
    Node leader();

    /**
     * Get proxy tail node
     */
    Node proxyTail();

    /**
     * Number of nodes left in validating set
     */
    int numberOfNodesLeftInValidatingSet();
}
