package bl.data;

import bchain.data.*;
import bl.data.get.GetReply;
import bl.data.get.GetVariableRequest;
import bl.data.set.SetReply;
import bl.data.set.SetVariableRequest;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Class contains global link to parser
 */
public class JsonTransport {

    public static Gson deserializer = new GsonBuilder()
            .registerTypeAdapter(Message.class, new MessageDeserializer())
            .registerTypeAdapter(RequestMessage.class, new MessageDeserializer())
            .registerTypeAdapter(ReplyMessage.class, new MessageDeserializer())
            .setLenient()
            .create();

    public static Gson serializer = new GsonBuilder()
            .setLenient()
            .create();

    public static ChainMessage.ChainSerializeFactory jsonChainFactory = new ChainMessage.ChainSerializeFactory() {
        @Override
        public String serialize(ChainMessage chain) {
            return serializer.toJson(chain);
        }
    };

    public static AckMessage.AckSerializeFactory jsonAckFactory = new AckMessage.AckSerializeFactory() {
        @Override
        public String serialize(AckMessage ack) {
            return serializer.toJson(ack);
        }
    };

    public static ReplyMessage.ReplySerializeFactory jsonReplyFactory = new ReplyMessage.ReplySerializeFactory() {
        @Override
        public String serialize(ReplyMessage reply) {
            return serializer.toJson(reply);
        }
    };

    public static RequestMessage.RequestSerializeFactory jsonRequestFactory = new RequestMessage.RequestSerializeFactory() {
        @Override
        public String serialize(RequestMessage request) {
            return serializer.toJson(request);
        }
    };

    static {
        ChainMessage.factory = jsonChainFactory;
        AckMessage.factory = jsonAckFactory;
        ReplyMessage.factory = jsonReplyFactory;

    }

    private static class MessageDeserializer implements JsonDeserializer<Message> {

        private static Map<String, Class<? extends Message>> registeredTypes = new HashMap<String,
                Class<? extends Message>>() {
            private HashMap<String, Class<? extends Message>> inflate() {
                // set op
                put(SetVariableRequest.class.getSimpleName(), SetVariableRequest.class);
                put(SetReply.class.getSimpleName(), SetReply.class);

                // get op
                put(GetVariableRequest.class.getSimpleName(), GetVariableRequest.class);
                put(GetReply.class.getSimpleName(), GetReply.class);

                return this;
            }
        }.inflate();

        @Override
        public Message deserialize(JsonElement el, Type type, JsonDeserializationContext context)
                throws JsonParseException {
            String rawType = el.getAsJsonObject().get("type").getAsString();
            if (rawType != null) {
                Class<? extends Message> realClass = registeredTypes.get(rawType);
                if (realClass == null) {
                    throw new RuntimeException("Not matched type: " + rawType);
                }
                return context.deserialize(el, realClass);
            }
            return null;
        }
    }
}
