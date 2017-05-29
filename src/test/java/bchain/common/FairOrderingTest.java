package bchain.common;

import bchain.data.Node;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static bchain.data.Node.node;
import static org.junit.Assert.*;

/**
 * Tests for ordering
 */
public class FairOrderingTest {

    private FairOrdering order;

    private List<Node> clusterOrder = Lists.immutable.of(
            node("0", null), // leader
            node("1", null),
            node("2", null),
            node("3", null),
            node("4", null), // proxy tail
            node("5", null), // starts set of non-validating nodes
            node("6", null)
    ).toList();

    @Before
    public void setUp() throws Exception {
        order = new FairOrdering();
        order.setOrder(clusterOrder);
    }

    @Test
    public void successor() throws Exception {
        order.setMyNode(clusterOrder.get(1));
        assertEquals(clusterOrder.get(2), order.successor());
    }

    @Test
    public void predecessor() throws Exception {
        order.setMyNode(clusterOrder.get(1));
        assertEquals(clusterOrder.get(0), order.predecessor());
    }

    @Test
    public void getMirrorFromNodeNonValidationSet() throws Exception {
        order.setMyNode(clusterOrder.get(4));
        assertEquals(clusterOrder.get(5), order.getMirrorFromNodeNonValidationSet());

        order.setMyNode(clusterOrder.get(3));
        assertEquals(clusterOrder.get(6), order.getMirrorFromNodeNonValidationSet());

        order.setMyNode(clusterOrder.get(2));
        assertEquals(null, order.getMirrorFromNodeNonValidationSet());

        order.setMyNode(clusterOrder.get(5));
        assertEquals(null, order.getMirrorFromNodeNonValidationSet());
    }

    @Test
    public void iAmLeader() throws Exception {
        order.setMyNode(clusterOrder.get(1));
        assertFalse(order.iAmLeader());

        order.setMyNode(clusterOrder.get(0));
        assertTrue(order.iAmLeader());
    }

    @Test
    public void iAmProxyTail() throws Exception {
        order.setMyNode(clusterOrder.get(1));
        assertFalse(order.iAmProxyTail());

        order.setMyNode(clusterOrder.get(4));
        assertTrue(order.iAmProxyTail());
    }

    @Test
    public void iAmFromValidateSet() throws Exception {
        order.setMyNode(clusterOrder.get(4));
        assertTrue(order.iAmFromValidateSet());

        order.setMyNode(clusterOrder.get(5));
        assertFalse(order.iAmFromValidateSet());
    }

    @Test
    public void leader() throws Exception {
        assertEquals(clusterOrder.get(0), order.leader());
    }

    @Test
    public void proxyTail() throws Exception {
        assertEquals(clusterOrder.get(4), order.proxyTail());
    }

    @Test
    public void leftNodesTest() throws Exception {
        order.setMyNode(clusterOrder.get(0)); // set leader as my node
        Assert.assertEquals(4, order.numberOfNodesLeftInValidatingSet());

        order.setMyNode(clusterOrder.get(4)); // set proxy tail as my node
        Assert.assertEquals(0, order.numberOfNodesLeftInValidatingSet());

        order.setMyNode(clusterOrder.get(2)); // set simple node from validation set as my node
        Assert.assertEquals(2, order.numberOfNodesLeftInValidatingSet());
    }
}