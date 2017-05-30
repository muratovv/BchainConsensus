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
    public void onRequest(RequestMessage message, Client client) {
        processing.match(message);
        if (ordering.iAmLeader()) {
            onLeaderRequest(message, client);
        } else {
            onOtherNodeRequest(message, client);
        }
    }

    @Override
    public void onChain(ChainMessage chain) {
        processing.match(chain);
        sendChainRequest(chain);
        ifProxyChain(chain);
    }

    private void sendChainRequest(ChainMessage message) {
        if (ordering.iAmFromValidateSet()) {
            Node next = ordering.successor();
            transport.send(next, message.toTransport());
        }
        if (ordering.iAmFromValidateSet() && !ordering.iAmProxyTail()) {
            timer.start();
        }
    }

    private void ifProxyChain(ChainMessage message) {
        if (ordering.iAmProxyTail()) {
            replayClient(message);
            ackInstantiation(message);
        }
    }

    private void replayClient(ChainMessage message) {
        // TODO 29.05.17 to - it is client
        transport.send(message.getClientInformation(), message.makeReplyMessage().toTransport());
    }

    private void ackInstantiation(ChainMessage message) {
        transport.send(ordering.predecessor(), message.makeAckMessage().toTransport());
    }

    @Override
    public void onAck(AckMessage message) {
        processing.match(message);
        Node mirror = ordering.getMirrorFromNodeNonValidationSet();
        if (mirror != null) {
            // message - chain request
            transport.send(mirror, message.retrieveChainMessage().toTransport());
        }
        if (ordering.iAmFromValidateSet() && !ordering.iAmProxyTail()) {
            timer.deny();
        }
    }

    @Override
    public void onFailure() {
        // TODO 29.05.17 onFailure should invoke re-chaining protocol
        // TODO: implement onFailure
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onLeaderRequest(RequestMessage message, Client client) {
        Node successor = ordering.successor();
        transport.send(successor, ChainMessage.chain(message, client).toTransport());
    }

    @Override
    public void onOtherNodeRequest(RequestMessage request, Client client) {
        // TODO: implement onOtherNodeRequest
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
