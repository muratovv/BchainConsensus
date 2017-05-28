package bchain.chaining;

import bchain.data.AckMessage;
import bchain.data.ChainMessage;
import bchain.data.RequestMessage;

/**
 * Fair implementation of {@link ChainingProtocol}
 */
public class FairChainingProtocol implements ChainingProtocol, LeaderRedirectStrategy {
    @Override
    public void onRequest(RequestMessage message) {
        // TODO: implement onRequest
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onChain(ChainMessage message) {
        // TODO: implement onChain
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onAck(AckMessage message) {
        // TODO: implement onAck
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onFailure() {
        // TODO: implement onFailure
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onLeaderRequest(RequestMessage message) {
        // TODO: implement onLeaderRequest
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onOtherNodeRequest(RequestMessage message) {
        // TODO: implement onOtherNodeRequest
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
