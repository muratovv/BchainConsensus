package bl.data;

import bchain.data.Message;
import bl.data.set.SetReply;
import bl.data.set.SetVariableRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@code bl.data.*}
 */
public class JsonParserTest {
    @Test
    public void correctHierarchyTest() throws Exception {
        SetReply reply = new SetReply();
        reply.status = "OK";
        reply.variable = "var";

        SetVariableRequest request = new SetVariableRequest();
        request.value = "23E3F$R";
        request.variable = "var";

        String replyRaw   = JsonParser.gson.toJson(reply);
        String requestRaw = JsonParser.gson.toJson(request);

        Assert.assertEquals(reply, JsonParser.gson.fromJson(replyRaw, Message.class));
        Assert.assertEquals(request, JsonParser.gson.fromJson(requestRaw, Message.class));
    }
}