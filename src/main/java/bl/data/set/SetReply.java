package bl.data.set;

import bchain.data.ReplyMessage;
import bl.data.JsonTransport;

import java.util.Objects;

/**
 * Data class for reply to client on set request
 */
public class SetReply extends ReplyMessage {
    public String variable;
    public String status;

    @Override
    public String toTransport() {
        return JsonTransport.gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetReply)) return false;
        SetReply reply = (SetReply) o;
        return Objects.equals(variable, reply.variable) &&
                Objects.equals(status, reply.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, status);
    }
}
