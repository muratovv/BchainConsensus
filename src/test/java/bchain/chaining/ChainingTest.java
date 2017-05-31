package bchain.chaining;

import bchain.common.*;
import bchain.data.*;
import bl.ObjectProcessing;
import bl.data.JsonTransport;
import bl.data.set.SetReply;
import bl.data.set.SetVariableRequest;
import cluster.transport.MessageListener;
import cluster.transport.Transport;
import deferred_queue.core.Delay;
import javafx.util.Pair;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static bchain.data.ChainMessage.chain;
import static bchain.data.Node.node;
import static org.junit.Assert.assertEquals;

/**
 * Testing for verify correct behaviour of {@link FairChainingProtocol}
 */
public class ChainingTest {
    private List<Node> clusterOrder = Lists.immutable.of(
            node("0", null), // leader
            node("1", null),
            node("2", null),
            node("3", null),
            node("4", null), // proxy tail
            node("5", null), // starts set of non-validating nodes
            node("6", null)
    ).toList();

    private OrderingBuilder builder    = new FairOrderingBuilder().setOrder(clusterOrder);
    private TimerStub       timer      = new TimerStub();
    private ProcessingStub  processing = new ProcessingStub();
    private TransportStub   transport  = new TransportStub();

    private SetVariableRequest request;

    @Before
    public void setUp() throws Exception {
        JsonTransport.deserializer.toString();
        request = new SetVariableRequest();
        request.variable = "test";
        request.value = "TEST";
    }

    @Test
    public void onLeaderRequest() throws Exception {
        ChainingProtocol protocol = new FairChainingProtocol(processing, initLeader(),
                transport, timer);
        protocol.onRequest(request);

        // processing checking
        assertEquals(1, processing.matched.size());
        assertEquals(request, processing.matched.get(0));

        // transport checking
        assertEquals(1, transport.sends.size());
        assertEquals(clusterOrder.get(1), transport.sends.get(0).getKey());
        assertEquals(chain(request),
                JsonTransport.deserializer.fromJson(transport.sends.get(0).getValue(), ChainMessage.class));

        // timer checking
        // TODO 31.05.17 write timer's assertions
    }

    private Ordering initLeader() {
        return builder.setMyNode(clusterOrder.get(0)).build();
    }

    @Test
    public void validationNodeTest() throws Exception {
        Ordering ordering = initNodeFromValidatingSet();
        ChainingProtocol protocol = new FairChainingProtocol(processing, ordering,
                transport, timer);
        protocol.onChain(chain(request));

        // processing checking
        assertEquals(1, processing.matched.size());
        assertEquals(request, processing.matched.get(0));

        // transport checking
        assertEquals(1, transport.sends.size());
        assertEquals(ordering.successor(), transport.sends.get(0).getKey());
        assertEquals(chain(request),
                JsonTransport.deserializer.fromJson(transport.sends.get(0).getValue(), ChainMessage.class));

        // timer checking
        // TODO 31.05.17 write timer's assertions
    }

    private Ordering initNodeFromValidatingSet() {
        return builder.setMyNode(clusterOrder.get(2)).build();
    }

    @Test
    public void proxyTailTest() throws Exception {
        Ordering ordering = initProxyTail();
        ChainingProtocol protocol = new FairChainingProtocol(processing, ordering,
                transport, timer);
        ChainMessage inputChain = chain(request);
        protocol.onChain(inputChain);

        // processing checking
        assertEquals(1, processing.matched.size());
        assertEquals(request, processing.matched.get(0));

        // transport checking
        // note: checking ordering implementation dependent
        assertEquals(3, transport.sends.size());

        // chain
        assertEquals(ordering.successor(), transport.sends.get(0).getKey());
        assertEquals(inputChain,
                JsonTransport.deserializer.fromJson(transport.sends.get(0).getValue(), ChainMessage.class));

        // client
        assertEquals(null, transport.sends.get(1).getKey());
        assertEquals(processing.getAnswer(),
                JsonTransport.deserializer.fromJson(transport.sends.get(1).getValue(), ReplyMessage.class));

        // ack
        assertEquals(ordering.predecessor(), transport.sends.get(2).getKey());
        assertEquals(AckMessage.ack(inputChain),
                JsonTransport.deserializer.fromJson(transport.sends.get(2).getValue(), AckMessage.class));

        // timer checking
        // TODO 31.05.17 write timer's assertions
    }

    private Ordering initProxyTail() {
        return builder.setMyNode(clusterOrder.get(4)).build();
    }

    private Ordering initNodeFromNonValidationSet() {
        return builder.setMyNode(clusterOrder.get(6)).build();
    }

    private static class TransportStub implements Transport {

        public List<Pair<Object, String>> sends = new ArrayList<>();

        @Override
        public void send(Node to, String message) {
            sends.add(new Pair<>(to, message));
        }

        @Override
        public void send(Client to, String message) {
            sends.add(new Pair<>(to, message));
        }

        @Override
        public void setMessageListener(MessageListener listener) {

        }
    }

    private static class TimerStub implements Timer {
        @Override
        public Timer setTime(Delay delay) {
            return this;
        }

        @Override
        public Timer setDelegate(Delegate delegate) {
            return this;
        }

        @Override
        public void start() {

        }

        @Override
        public void deny() {

        }
    }

    private static class ProcessingStub implements ObjectProcessing {

        private List<Object> matched = new ArrayList<>();

        @Override
        public void match(Object object) {
            matched.add(object);
        }

        @Override
        public ReplyMessage getAnswer() {
            SetReply reply = new SetReply();
            reply.variable = "test";
            reply.status = "OK";
            return reply;
        }
    }


}