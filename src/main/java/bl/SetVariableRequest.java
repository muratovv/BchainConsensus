package bl;

import bchain.data.RequestMessage;

/**
 * Data class for setting message to cluster
 */
public class SetVariableRequest extends RequestMessage {
    public String setRequestVariable;
    public String setRequestValue;
}
