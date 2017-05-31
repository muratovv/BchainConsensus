package bl.data.get;

import bchain.data.ReplyMessage;
import bl.data.JsonTransport;

import java.util.Objects;

/**
 * Data class for reply to client on get request
 */
public class GetReply extends ReplyMessage {
    public String variable;
    public String status;

    @Override
    public String toTransport() {
        return JsonTransport.gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetReply)) return false;
        GetReply getReply = (GetReply) o;
        return Objects.equals(variable, getReply.variable) &&
                Objects.equals(status, getReply.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, status);
    }
}
