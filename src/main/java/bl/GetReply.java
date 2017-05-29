package bl;

import bchain.data.ReplyMessage;

/**
 * Data class for reply to client on get request
 */
public class GetReply extends ReplyMessage {
    public String getReplyVariable;
    public String getReplyValue;

    @Override
    public String toTransport() {
        // TODO: implement toTransport
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
