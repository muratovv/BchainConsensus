package bl.data.get;

import bchain.data.RequestMessage;
import bl.data.JsonTransport;

import java.util.Objects;

/**
 * Data class for getting variable from cluster
 */
public class GetVariableRequest extends RequestMessage {
    public String variable;

    @Override
    public String toTransport() {
        return JsonTransport.gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetVariableRequest)) return false;
        GetVariableRequest that = (GetVariableRequest) o;
        return Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }
}
