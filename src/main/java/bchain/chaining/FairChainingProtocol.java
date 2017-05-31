package bchain.chaining;

import bchain.common.Ordering;
import bchain.common.Timer;
import bchain.data.*;
import bl.ObjectProcessing;
import cluster.transport.Transport;

/**
 * Fair implementation of {@link ChainingProtocol}
 */
public class FairChainingProtocol implements ChainingProtocol, LeaderRedirectStrategy {

    private final Transport        transport;
    private final Timer            timer;    // assume that timer.setDelay() already invoked
    private final Ordering         ordering; // assume that ordering.setMyNode() already invoked
    private final ObjectProcessing processing;

    public FairChainingProtocol(ObjectProcessing processing, Ordering ordering, Transport transport,
                                Timer timer) {
        this.transport = transport;
        this.timer = timer;
        this.timer.setDelegate(this::onFailure);
        this.ordering = ordering;
        this.processing = processing;
    }

    @Override
    public void onRequest(RequestMessage request) {
        processing.match(request, ordering.iAmFromValidateSet() && !ordering.iAmProxyTail());
        if (ordering.iAmLeader()) {
            onLeaderRequest(request);
        } else {
            onOtherNodeRequest(request);
        }
    }

    @Override
    public void onChain(ChainMessage chain) {
        processing.match(chain.request, !(ordering.iAmFromValidateSet() && !ordering.iAmProxyTail()));
        sendChainRequest(chain);
        ifProxyChain(chain);
    }

    private void sendChainRequest(ChainMessage chain) {
        if (ordering.iAmFromValidateSet()) {
            Node next = ordering.successor();
            transport.send(next, ChainMessage.factory.serialize(chain));
        }
        if (ordering.iAmFromValidateSet() && !ordering.iAmProxyTail()) {
            timer.start();
        }
    }

    private void ifProxyChain(ChainMessage message) {
        if (ordering.iAmProxyTail()) {
            replayToClient(message, processing.getAnswer());
            ackInstantiation(message);
        }
    }

    private void replayToClient(ChainMessage chain, ReplyMessage reply) {
        transport.send(chain.request.client, ReplyMessage.factory.serialize(reply));
    }

    private void ackInstantiation(ChainMessage chain) {
        transport.send(ordering.predecessor(), AckMessage.factory.serialize(AckMessage.ack(chain)));
    }

    @Override
    public void onAck(AckMessage ack) {
        Node mirror = ordering.getMirrorFromNodeNonValidationSet();
        if (mirror != null) {
            transport.send(mirror, ChainMessage.factory.serialize(ack.chain));
        }
        if (ordering.iAmFromValidateSet() && !ordering.iAmProxyTail()) {
            timer.deny();
            processing.match(ack.chain.request, true);
        }
    }

    @Override
    public void onFailure() {
        // TODO 29.05.17 onFailure should invoke re-chaining protocol
        // TODO: implement onFailure
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onLeaderRequest(RequestMessage message) {
        Node successor = ordering.successor();
        transport.send(successor, ChainMessage.factory.serialize(ChainMessage.chain(message)));
    }

    @Override
    public void onOtherNodeRequest(RequestMessage request) {
        // TODO: implement onOtherNodeRequest
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
