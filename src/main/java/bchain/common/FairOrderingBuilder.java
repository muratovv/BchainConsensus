package bchain.common;

import bchain.data.Node;

import java.util.List;

/**
 * Builder that create {@link FairOrdering}
 */
public class FairOrderingBuilder implements OrderingBuilder {
    private List<Node> list;
    private Node       myNode;


    @Override
    public OrderingBuilder setOrder(List<Node> list) {
        this.list = list;
        return this;
    }

    @Override
    public OrderingBuilder setMyNode(Node node) {
        this.myNode = node;
        return this;
    }

    @Override
    public Ordering build() {
        return new FairOrdering(list, myNode);
    }
}
