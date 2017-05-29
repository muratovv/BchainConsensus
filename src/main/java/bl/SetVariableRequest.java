package bl;

import bchain.data.ChainMessage;
import bchain.data.RequestMessage;

/**
 * Data class for setting message to cluster
 */
public class SetVariableRequest extends RequestMessage {
    public String setRequestVariable;
    public String setRequestValue;

    @Override
    public String toTransport() {
        // TODO: implement toTransport
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ChainMessage makeChainMessage() {
        // TODO: implement makeChainMessage
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
