package bchain.common;

import bchain.data.Node;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static bchain.data.Node.node;
import static org.junit.Assert.*;

/**
 * Tests for ordering
 */
public class FairOrderingTest {

    private Ordering order;

    private List<Node>      clusterOrder = Lists.immutable.of(
            node("0", null), // leader
            node("1", null),
            node("2", null),
            node("3", null),
            node("4", null), // proxy tail
            node("5", null), // starts set of non-validating nodes
            node("6", null)
    ).toList();
    private OrderingBuilder builder      = new FairOrderingBuilder().setOrder(clusterOrder);


    @Test
    public void successor() throws Exception {
        order = builder.setMyNode(clusterOrder.get(1)).build();
        assertEquals(clusterOrder.get(2), order.successor());
    }

    @Test
    public void predecessor() throws Exception {
        order = builder.setMyNode(clusterOrder.get(1)).build();
        assertEquals(clusterOrder.get(0), order.predecessor());
    }

    @Test
    public void getMirrorFromNodeNonValidationSet() throws Exception {
        order = builder.setMyNode(clusterOrder.get(4)).build();
        assertEquals(clusterOrder.get(5), order.getMirrorFromNodeNonValidationSet());

        order = builder.setMyNode(clusterOrder.get(3)).build();
        assertEquals(clusterOrder.get(6), order.getMirrorFromNodeNonValidationSet());

        order = builder.setMyNode(clusterOrder.get(2)).build();
        assertEquals(null, order.getMirrorFromNodeNonValidationSet());

        order = builder.setMyNode(clusterOrder.get(5)).build();
        assertEquals(null, order.getMirrorFromNodeNonValidationSet());
    }

    @Test
    public void iAmLeader() throws Exception {
        order = builder.setMyNode(clusterOrder.get(1)).build();
        assertFalse(order.iAmLeader());

        order = builder.setMyNode(clusterOrder.get(0)).build();
        assertTrue(order.iAmLeader());
    }

    @Test
    public void iAmProxyTail() throws Exception {
        order = builder.setMyNode(clusterOrder.get(1)).build();
        assertFalse(order.iAmProxyTail());

        order = builder.setMyNode(clusterOrder.get(4)).build();
        assertTrue(order.iAmProxyTail());
    }

    @Test
    public void iAmFromValidateSet() throws Exception {
        order = builder.setMyNode(clusterOrder.get(4)).build();
        assertTrue(order.iAmFromValidateSet());

        order = builder.setMyNode(clusterOrder.get(5)).build();
        assertFalse(order.iAmFromValidateSet());
    }

    @Test
    public void leader() throws Exception {
        order = builder.setMyNode(clusterOrder.get(1)).build();
        assertEquals(clusterOrder.get(0), order.leader());
    }

    @Test
    public void proxyTail() throws Exception {
        order = builder.setMyNode(clusterOrder.get(1)).build();
        assertEquals(clusterOrder.get(4), order.proxyTail());
    }

    @Test
    public void leftNodesTest() throws Exception {
        order = builder.setMyNode(clusterOrder.get(0)).build(); // set leader as my node
        Assert.assertEquals(4, order.numberOfNodesLeftInValidatingSet());

        order = builder.setMyNode(clusterOrder.get(4)).build(); // set proxy tail as my node
        Assert.assertEquals(0, order.numberOfNodesLeftInValidatingSet());

        order = builder.setMyNode(clusterOrder.get(2)).build(); // set simple node from validation set as my node
        Assert.assertEquals(2, order.numberOfNodesLeftInValidatingSet());
    }
}