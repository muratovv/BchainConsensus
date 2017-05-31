package bchain.data;

/**
 * REPLY from chaining protocol
 */
public abstract class ReplyMessage extends Message {

    public static ReplySerializeFactory factory;
    public        RequestMessage        request;

    public abstract static class ReplySerializeFactory {
        public abstract String serialize(ReplyMessage reply);
    }
}
