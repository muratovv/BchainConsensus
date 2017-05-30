package bl.data.get;

import bchain.data.RequestMessage;

/**
 * Data class for getting variable from cluster
 */
public class GetVariableRequest extends RequestMessage {
    public String getRequestVariable;

    @Override
    public String toTransport() {
        // TODO: implement toTransport
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
