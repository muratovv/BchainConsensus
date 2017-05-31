package bl.data.get;

import bchain.data.RequestMessage;

import java.util.Objects;

/**
 * Data class for getting variable from cluster
 */
public class GetVariableRequest extends RequestMessage {
    public String variable;

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetVariableRequest)) return false;
        GetVariableRequest that = (GetVariableRequest) o;
        return Objects.equals(variable, that.variable);
    }
}
