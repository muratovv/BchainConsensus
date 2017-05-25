package bchain.common;

import bchain.data.Node;

import java.util.List;

/**
 * Fair implementation of {@link Ordering}
 */
public class FairOrdering implements Ordering {
    @Override
    public FairOrdering setOrder(List<Node> list) {
        // TODO: implement setOrder
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setMyNode(Node node) {
        // TODO: implement setMyNode
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Node successor() {
        // TODO: implement successor
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Node predecessor() {
        // TODO: implement predecessor
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Node getMirrorFromNodeNonValidationSet() {
        // TODO: implement getMirrorFromNodeNonValidationSet
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean iAmLeader() {
        // TODO: implement iAmLeader
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean iAmProxyTail() {
        // TODO: implement iAmProxyTail
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean iAmFromValidateSet() {
        // TODO: implement iAmFromValidateSet
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
