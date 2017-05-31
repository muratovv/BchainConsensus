package bl.data;

import bchain.data.Message;
import bl.data.set.SetReply;
import bl.data.set.SetVariableRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@code bl.data.*}
 */
public class JsonTransportTest {
    @Test
    public void correctHierarchyTest() throws Exception {
        SetReply reply = new SetReply();
        reply.status = "OK";
        reply.variable = "var";

        SetVariableRequest request = new SetVariableRequest();
        request.value = "23E3F$R";
        request.variable = "var";

        String replyRaw   = JsonTransport.jsonReplyFactory.serialize(reply);
        String requestRaw = JsonTransport.jsonRequestFactory.serialize(request);

        Assert.assertEquals(reply, JsonTransport.deserializer.fromJson(replyRaw, Message.class));
        Assert.assertEquals(request, JsonTransport.deserializer.fromJson(requestRaw, Message.class));
    }
}