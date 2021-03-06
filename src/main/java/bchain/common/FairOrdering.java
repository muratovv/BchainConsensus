package bchain.common;

import bchain.data.Node;

import java.util.List;

/**
 * Fair implementation of {@link Ordering}
 */
public class FairOrdering implements Ordering {

    private Integer    myPosition;
    private List<Node> order;

    public FairOrdering(List<Node> list, Node node) {
        setOrder(list).setMyNode(node);
    }

    private void setMyNode(Node node) {
        for (int i = 0; i < order.size(); i++) {
            if (order.get(i).equals(node)) {
                myPosition = i;
            }
        }
    }

    private FairOrdering setOrder(List<Node> list) {
        this.order = list;
        invalidatePosition();
        return this;
    }

    private void invalidatePosition() {
        this.myPosition = null;
    }

    @Override
    public Node successor() {
        return myPosition != order.size() - 1 ? order.get(myPosition + 1) : null;
    }

    @Override
    public Node predecessor() {
        return myPosition != 0 ? order.get(myPosition - 1) : null;
    }

    @Override
    public Node getMirrorFromNodeNonValidationSet() {
        if (myPosition > proxyPosition()) return null;
        int offset    = proxyPosition() - myPosition + 1;
        int mirrorPos = proxyPosition() + offset;
        if (mirrorPos >= order.size()) {
            return null;
        } else return order.get(mirrorPos);
    }

    @Override
    public boolean iAmLeader() {
        return myPosition == 0;
    }

    @Override
    public boolean iAmProxyTail() {
        return myPosition == proxyPosition();
    }

    @Override
    public boolean iAmFromValidateSet() {
        return myPosition <= proxyPosition();
    }

    @Override
    public Node leader() {
        return order.get(0);
    }

    @Override
    public Node proxyTail() {
        return order.get(proxyPosition());
    }

    @Override
    public int numberOfNodesLeftInValidatingSet() {
        return order.indexOf(proxyTail()) - myPosition;
    }

    private int proxyPosition() {
        int faultNodes = numberOfFaultNodes();
        return order.size() - faultNodes - 1;
    }

    private int numberOfFaultNodes() {
        int N = order.size();
        return (N - 1) / 3; // divide with floor
    }
}
