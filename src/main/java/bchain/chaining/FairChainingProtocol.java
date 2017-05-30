package bchain.chaining;

import bchain.common.Ordering;
import bchain.common.Timer;
import bchain.data.AckMessage;
import bchain.data.ChainMessage;
import bchain.data.Node;
import bchain.data.RequestMessage;
import bl.ObjectProcessing;
import cluster.transport.Transport;

import static bchain.data.ReplyMessage.reply;

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
        processing.match(request);
        if (ordering.iAmLeader()) {
            onLeaderRequest(request);
        } else {
            onOtherNodeRequest(request);
        }
    }

    @Override
    public void onChain(ChainMessage chain) {
        processing.match(chain.getRequest());
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
            replayToClient(message);
            ackInstantiation(message);
        }
    }

    private void replayToClient(ChainMessage chain) {
        // TODO 29.05.17 to - it is client
        transport.send(chain.getRequest().getClientInformation(), reply(chain).toTransport());
    }

    private void ackInstantiation(ChainMessage chain) {
        transport.send(ordering.predecessor(), AckMessage.ack(chain).toTransport());
    }

    @Override
    public void onAck(AckMessage ack) {
        Node mirror = ordering.getMirrorFromNodeNonValidationSet();
        if (mirror != null) {
            // message - chain request
            transport.send(mirror, ack.retrieveChainMessage().toTransport());
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
    public void onLeaderRequest(RequestMessage message) {
        Node successor = ordering.successor();
        transport.send(successor, ChainMessage.chain(message).toTransport());
    }

    @Override
    public void onOtherNodeRequest(RequestMessage request) {
        // TODO: implement onOtherNodeRequest
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
